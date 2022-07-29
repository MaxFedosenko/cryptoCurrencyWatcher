package com.idf.controllers;

import com.idf.entities.CryptoCurrency;
import com.idf.services.impl.CryptoServiceImpl;
import com.idf.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/")
public class CryptoCurrencyController {
    
    @Autowired
    private CryptoServiceImpl cryptoService;
    
    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping
    public ResponseEntity<List<CryptoCurrency>> getAll() {
        return ResponseEntity.ok().body(cryptoService.getAll());
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity<CryptoCurrency> getById(@PathVariable("id") Long  id) {
        return ResponseEntity.ok().body(cryptoService.getById(id));
    }
    
    @PostMapping("{userName}/{symbol}")
    public ResponseEntity<Double> notify(@PathVariable("userName") String  userName,
                                       @PathVariable("symbol") String symbol) {
        return ResponseEntity.ok().body(userService.notify(userName, symbol).getPriceUsd());
    }
    

}
