package com.example.demo.controller;


import com.example.demo.dto.URLDto;
import com.example.demo.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class URLController {
    @Autowired
    private URLService urlService;

    @PostMapping("create_url")
    public ResponseEntity<String> createURL(@RequestBody URLDto urlDto){
        urlService.createShortURL(urlDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        String longUrl = urlService.getLongUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", longUrl);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
