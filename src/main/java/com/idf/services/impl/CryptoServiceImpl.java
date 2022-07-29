package com.idf.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idf.entities.CryptoCurrency;
import com.idf.repositories.CryptoRepository;
import com.idf.services.CryptoService;
import com.idf.services.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CryptoServiceImpl implements CryptoService{
    
    @Autowired
    private CryptoRepository cryptoRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private FeignClientService feignService;
    
    public CryptoCurrency getById(Long id) {
        return cryptoRepository.findById(id).get();
    }
    
    public List<CryptoCurrency> getAll() {
        return cryptoRepository.findAll();
    }
    
    @Scheduled(fixedDelay = 5000)
    public void scheduledUpdate() throws JsonProcessingException {
        List<CryptoCurrency> cryptoCurrencies = cryptoRepository.findAll();
        int size = cryptoCurrencies.size();
        for (int i = 0; i < size; i++) {
            String cryptoCurrency = feignService.getById(cryptoCurrencies.get(i).getId());
            List<Map<String, Object>> maps = objectMapper.readValue(cryptoCurrency, new TypeReference<List<Map<String, Object>>>(){});
            Object priceUsd = maps.get(0).get("price_usd");
            double price = Double.parseDouble(priceUsd.toString());
            cryptoRepository.update(price, cryptoCurrencies.get(i).getId());
        }
    }
    
    
}
