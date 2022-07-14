package com.idf.services.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.idf.entities.CryptoCurrency;
import com.idf.repositories.CryptoRepository;
import com.idf.services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {
    
    @Autowired
    CryptoRepository cryptoRepository;
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    public ResponseEntity<String> getAll() {
        return restTemplate().getForEntity("https://api.coinlore.net/api/ticker/?id=80,90,48543", String.class);
    }
    
    public ResponseEntity<String> getOne(Long id) {
        return restTemplate().getForEntity("https://api.coinlore.net/api/ticker/?id=" + id, String.class);
    }
    
    @Scheduled(cron = "0 * * * * *")
    public void scheduledUpdate() {
        List<CryptoCurrency> cryptoCurrencies = cryptoRepository.findAll();
        int size = cryptoCurrencies.size();
        for (int i = 0; i < size; i++) {
            String body = getOne(cryptoCurrencies.get(i).getId()).getBody();
            String bodyToJSON = body.substring(1, body.length() - 1);
            JsonElement element = new JsonParser().parse(bodyToJSON);
            String price = element.getAsJsonObject().get("price_usd").getAsString();
            double priceUsd = Double.parseDouble(price);
            cryptoRepository.update(priceUsd, cryptoCurrencies.get(i).getId());
        }
    }
    
}
