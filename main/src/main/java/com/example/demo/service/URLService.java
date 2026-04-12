package com.example.demo.service;


import com.example.demo.dao.URLDao;
import com.example.demo.dto.URLDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class URLService {

    @Autowired
    private URLDao urlDao;

    public ResponseEntity<String> createURLEntry(@RequestBody URLDto urlDto){
        return ResponseEntity.ok();
    }
}
