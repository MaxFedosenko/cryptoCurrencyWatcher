package com.idf.controllers;

import com.idf.services.impl.CryptoServiceImpl;
import com.idf.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    CryptoServiceImpl cryptoService;
    
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<String> getAll() {
        return cryptoService.getAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<String> getOne(@PathVariable("id") Long id) {
        return cryptoService.getOne(id);
    }
    
    @PostMapping("{userName}/{symbol}")
    public ResponseEntity<Double> notify(@PathVariable("userName") String  userName,
                                         @PathVariable("symbol") String symbol) {
        return userService.notify(userName, symbol);
    }
    

}
