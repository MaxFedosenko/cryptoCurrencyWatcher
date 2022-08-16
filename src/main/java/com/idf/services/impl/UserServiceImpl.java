package com.idf.services.impl;


import com.idf.dto.UserDTO;
import com.idf.entities.CryptoCurrency;
import com.idf.entities.User;
import com.idf.exceptions.NotContentException;
import com.idf.mapper.UserMapper;
import com.idf.repositories.CryptoRepository;
import com.idf.repositories.UserRepository;
import com.idf.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final CryptoRepository cryptoRepository;
    
    private final UserMapper userMapper;
    
    public UserDTO notify(String userName, String symbol) {
        return userRepository.findByUserName(userName)
                .map(userMapper::toUserDTO)
                .orElseGet(() -> {
                    CryptoCurrency cryptoCurrency = cryptoRepository.findBySymbol(symbol)
                            .orElseThrow(() -> new NotContentException(String.format("Symbol %s does not exist", symbol)));
                    return userMapper.toUserDTO(userRepository.save(new User()
                            .setUserName(userName)
                            .setPriceUsd(cryptoCurrency.getPriceUsd())
                            .setCryptoCurrency(cryptoCurrency)));
                });
    }
    
    @Scheduled(cron = "0 * * * * *")
    public void scheduledMessage() {
        userRepository.findAll()
                .forEach(user -> {
                    double result = ((user.getCryptoCurrency().getPriceUsd() - user.getPriceUsd()) / user.getPriceUsd()) * 100;
                    String resultFormatted = String.format("%.2f", result);
                    if (result > 1) {
                        log.warn(String.format("%s цена на вашу криптовалюту %s выросла на %s%%",
                                user.getUserName(),
                                user.getCryptoCurrency().getSymbol(),
                                resultFormatted));
                    }
                });
    }
}
