package com.idf.mapper;

import com.idf.dto.CryptoCurrencyDTO;
import com.idf.entities.CryptoCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CryptoMapper{
    
    @Mapping(target = "cryptoId", source = "id")
    CryptoCurrencyDTO toCryptoCurrencyDTO(CryptoCurrency cryptoCurrency);
    
    @Mapping(target = "id", source = "cryptoId")
    CryptoCurrency toCryptoCurrency(CryptoCurrencyDTO cryptoCurrencyDTO);
    
}
