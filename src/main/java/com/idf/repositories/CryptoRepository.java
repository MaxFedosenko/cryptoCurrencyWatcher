package com.idf.repositories;

import com.idf.entities.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CryptoRepository extends JpaRepository<CryptoCurrency, Long> {
    
    List<CryptoCurrency> findAll();
    CryptoCurrency findBySymbol(String symbol);
    
    @Transactional
    @Modifying
    @Query("update CryptoCurrency set price_usd = :priceUsd where id = :id")
    void update(@Param("priceUsd") Double priceUsd,
                @Param("id") Long id);
}
