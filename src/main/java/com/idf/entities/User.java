package com.idf.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @ManyToOne(cascade = CascadeType.MERGE)
    private CryptoCurrency cryptoCurrency;
    private Double priceUsd;
    
    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }
    
    public User(String userName, Double priceUsd, CryptoCurrency cryptoCurrency) {
        this.userName = userName;
        this.priceUsd = priceUsd;
        this.cryptoCurrency = cryptoCurrency;
    }
    
    public User(Long id, String userName, CryptoCurrency cryptoCurrency) {
        this.id = id;
        this.userName = userName;
        this.cryptoCurrency = cryptoCurrency;
    }
    
    public User() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }
    
    public Double getPriceUsd() {
        return priceUsd;
    }
    
    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }
    
    
}
