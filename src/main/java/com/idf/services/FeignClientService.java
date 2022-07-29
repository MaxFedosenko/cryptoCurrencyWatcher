package com.idf.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feignClientService", url = "https://api.coinlore.net/api/ticker")
public interface FeignClientService {

    @GetMapping(value = "/?id={id}")
    String getById(@PathVariable ("id") Long id);

}
