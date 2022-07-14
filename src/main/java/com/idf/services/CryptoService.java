package com.idf.services;

import org.springframework.http.ResponseEntity;

public interface CryptoService {
    
    ResponseEntity<String> getAll();
    ResponseEntity<String> getOne(Long id);
    
}
