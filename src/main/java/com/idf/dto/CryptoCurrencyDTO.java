package com.idf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrencyDTO {
    
    @JsonProperty(value = "id")
    private Long cryptoId;
    @JsonProperty(value = "symbol")
    private String symbol;
    @JsonProperty(value = "price")
    private Double priceUsd;
    
}
