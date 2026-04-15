package com.example.demo.service;


import com.example.demo.dao.URLDao;
import com.example.demo.dto.URLDto;
import com.example.demo.model.URLModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Service
public class URLService {

    @Autowired
    private URLDao urlDao;

    @Transactional
    public String createShortURL(URLDto urlDto) {
        // Validate longUrl
        if (urlDto.getLongUrl() == null || urlDto.getLongUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("longUrl cannot be null or empty");
        }

        // Validate URL format
        if (!isValidURL(urlDto.getLongUrl().trim())) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        // Check for duplicate
        Optional<URLModel> existing = urlDao.findByLongUrl(urlDto.getLongUrl());
        if (existing.isPresent()) {
            return existing.get().getShortUrl();
        }

        // Create and save entity
        URLModel entity = new URLModel();
        entity.setLongUrl(urlDto.getLongUrl());
        entity.setProvidedName(urlDto.getProvidedName());
        URLModel saved = urlDao.save(entity);

        // Generate short URL
        String shortCode = encodeBase62(saved.getUuid());
        saved.setShortUrl(shortCode);
        urlDao.save(saved);

        return shortCode;
    }

    private String encodeBase62(UUID uuid) {
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        // Convert UUID to BigInteger for Base62 encoding
        BigInteger num = new BigInteger(uuid.toString().replace("-", ""), 16);

        while (num.compareTo(BigInteger.ZERO) > 0) {
            sb.append(alphabet.charAt(num.mod(BigInteger.valueOf(62)).intValue()));
            num = num.divide(BigInteger.valueOf(62));
        }
        return sb.reverse().toString();
    }

    private boolean isValidURL(String urlString) {
        try {
            new URL(urlString);
            return urlString.startsWith("http://") || urlString.startsWith("https://");
        } catch (Exception e) {
            return false;
        }
    }

    public String getLongUrl(String shortCode) {
        URLModel urlModel = urlDao.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        return urlModel.getLongUrl();
    }
}
