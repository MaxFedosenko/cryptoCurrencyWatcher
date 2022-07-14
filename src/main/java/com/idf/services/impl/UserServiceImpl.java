package com.idf.services.impl;


import com.idf.entities.CryptoCurrency;
import com.idf.entities.User;
import com.idf.repositories.CryptoRepository;
import com.idf.repositories.UserRepository;
import com.idf.services.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    CryptoRepository cryptoRepository;
    
    Logger logger = Logger.getLogger(this.getClass().getName());
    
    public ResponseEntity<Double> notify(String userName, String symbol) {
        CryptoCurrency bySymbol = cryptoRepository.findBySymbol(symbol);
        User userByName = userRepository.findByUserName(userName);
        if (userByName == null) {
            User savedUser = userRepository.save(new User(userName, bySymbol.getPriceUsd(), bySymbol));
            return ResponseEntity.ok().body(savedUser.getPriceUsd());
        } else {
            return ResponseEntity.ok().body(userByName.getPriceUsd());
        }
        
    }
    
    @Scheduled(fixedDelay = 5000)
    public void scheduledMessage() {
        List<User> users = userRepository.findAll();
        int size = users.size();
        double result;
        for (int i = 0; i < size; i++) {
            Double priceUsdInit = users.get(i).getPriceUsd();
            Double priceUsdChange = users.get(i).getCryptoCurrency().getPriceUsd();
            result = ((priceUsdChange - priceUsdInit) / priceUsdInit) * 100;
            if (result > 1) {
                logger.log(Logger.Level.WARN, users.get(i).getUserName() +
                        "Цена на вашу криптовалюту " +
                        users.get(i).getCryptoCurrency().getSymbol() +
                        " выросла на " + result + "%");
            }
        }
    }
    
}
