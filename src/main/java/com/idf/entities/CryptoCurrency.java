package com.idf.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;
    private String symbol;
    private Double priceUsd;
    
}
