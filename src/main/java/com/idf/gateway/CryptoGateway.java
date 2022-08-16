package com.idf.gateway;

import com.idf.dto.CryptoCurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "cryptoGateway", url = "https://api.coinlore.net/api/coin/markets")
public interface CryptoGateway {

    @GetMapping(value = "/?id={id}")
    List<CryptoCurrencyDTO> getById(@PathVariable ("id") Long id);

}
