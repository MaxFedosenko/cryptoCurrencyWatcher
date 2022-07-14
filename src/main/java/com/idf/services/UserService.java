package com.idf.services;

import org.springframework.http.ResponseEntity;

public interface UserService {
    
    ResponseEntity<Double> notify(String userName, String symbol);
    
}
