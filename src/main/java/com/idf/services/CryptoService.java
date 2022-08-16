package com.idf.services;

import com.idf.dto.CryptoCurrencyDTO;

import java.util.List;

public interface CryptoService {
    
    List<CryptoCurrencyDTO> getAll();
    CryptoCurrencyDTO getById(Long id);
    
}
