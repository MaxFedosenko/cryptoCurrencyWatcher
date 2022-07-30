package com.idf.services.impl;


import com.idf.entities.CryptoCurrency;
import com.idf.entities.User;
import com.idf.repositories.CryptoRepository;
import com.idf.repositories.UserRepository;
import com.idf.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CryptoRepository cryptoRepository;
    
    public User notify(String userName, String symbol) {
        CryptoCurrency bySymbol = cryptoRepository.findBySymbol(symbol);
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return userRepository.save(new User(null, userName, bySymbol, bySymbol.getPriceUsd()));
        }
        return user;
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
            String message = "%s цена на вашу криптовалюту %s выросла на %s%%";
            String userName = users.get(i).getUserName();
            String cryptoCurrency = users.get(i).getCryptoCurrency().getSymbol();
            String resultFormatted = String.format("%.2f", result);
            if (result > 1) {
                log.warn(String.format(message, userName, cryptoCurrency, resultFormatted));
            }
        }
    }
}
