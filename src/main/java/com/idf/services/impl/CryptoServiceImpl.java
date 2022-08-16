package com.idf.services.impl;

import com.idf.dto.CryptoCurrencyDTO;
import com.idf.entities.CryptoCurrency;
import com.idf.exceptions.NotFoundException;
import com.idf.mapper.CryptoMapper;
import com.idf.repositories.CryptoRepository;
import com.idf.services.CryptoService;
import com.idf.gateway.CryptoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CryptoServiceImpl implements CryptoService {
    
    private final CryptoRepository cryptoRepository;
    
    private final CryptoGateway cryptoGateway;
    
    private final CryptoMapper cryptoMapper;
    
    public CryptoCurrencyDTO getById(Long id) {
        return cryptoRepository.findById(id)
                .map(cryptoMapper::toCryptoCurrencyDTO)
                .orElseThrow(() -> new NotFoundException(String.format("Coin with id %s not found", id)));
    }
    
    public List<CryptoCurrencyDTO> getAll() {
        return cryptoRepository.findAll()
                .stream()
                .map(cryptoMapper::toCryptoCurrencyDTO)
                .collect(Collectors.toList());
    }
    
    @Scheduled(cron = "0 * * * * *")
    public void scheduledUpdate() {
        cryptoRepository.findAll()
                .forEach(currency -> {
                    CryptoCurrency cryptoCurrency = cryptoMapper.toCryptoCurrency(
                            cryptoGateway.getById(currency.getId())
                                    .stream()
                                    .findFirst()
                                    .orElseThrow(() ->
                                            new NotFoundException(
                                                    String.format("Crypto currency with id %s not found", currency.getId()))));
                    cryptoRepository.update(cryptoCurrency.getPriceUsd(), currency.getId());
                });
    }
}
