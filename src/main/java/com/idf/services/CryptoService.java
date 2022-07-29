package com.idf.services;

import com.idf.entities.CryptoCurrency;

import java.util.List;

public interface CryptoService {
    
    List<CryptoCurrency> getAll();
    CryptoCurrency getById(Long id);
    
}
