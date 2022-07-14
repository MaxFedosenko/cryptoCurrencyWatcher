package com.idf.entities;

import javax.persistence.*;
import java.util.List;

@Entity
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
    
    public CryptoCurrency() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Double getPriceUsd() {
        return priceUsd;
    }
    
    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }
    
    
    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", priceUsd=" + priceUsd +
                '}';
    }
}
