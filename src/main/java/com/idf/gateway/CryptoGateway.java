package com.idf.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cryptoGateway", url = "https://api.coinlore.net/api/ticker")
public interface CryptoGateway {

    @GetMapping(value = "/?id={id}")
    String getById(@PathVariable ("id") Long id);

}
