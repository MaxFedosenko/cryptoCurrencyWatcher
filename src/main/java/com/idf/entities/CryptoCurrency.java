package com.idf.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {

    @Id
    private Long id;
    private String symbol;
    private Double priceUsd;
    @OneToMany(mappedBy = "cryptoCurrency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;
    
    public CryptoCurrency(Long id, String symbol, Double priceUsd) {
        this.id = id;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }
}
