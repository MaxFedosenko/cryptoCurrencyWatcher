package com.idf.dto;

import com.idf.entities.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    private Long userId;
    private String userName;
    private CryptoCurrency cryptoCurrency;
    private Double priceUsd;
    
}
