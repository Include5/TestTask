package com.example.demo.crypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    Crypto findById(String id);

    Crypto findBySymbol(String symbol);

    @Modifying
    @Query("UPDATE Crypto c SET c.price_usd = ?2 WHERE c._id = ?1")
    void updateCrypto(Long id, String newInventoryAmount);
}
