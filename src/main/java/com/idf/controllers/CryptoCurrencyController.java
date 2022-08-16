package com.idf.controllers;

import com.idf.dto.CryptoCurrencyDTO;
import com.idf.dto.UserDTO;
import com.idf.services.impl.CryptoServiceImpl;
import com.idf.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CryptoCurrencyController {
    
    private final CryptoServiceImpl cryptoService;
    
    private final UserServiceImpl userService;
    
    @GetMapping
    public ResponseEntity<List<CryptoCurrencyDTO>> getAll() {
        return ResponseEntity.ok().body(cryptoService.getAll());
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity<CryptoCurrencyDTO> getById(@PathVariable("id") Long  id) {
        return ResponseEntity.ok().body(cryptoService.getById(id));
    }
    
    @PostMapping("{userName}/{symbol}")
    public ResponseEntity<UserDTO> notify(@PathVariable("userName") String  userName,
                                          @PathVariable("symbol") String symbol) {
        return ResponseEntity.ok().body(userService.notify(userName, symbol));
    }
    

}
