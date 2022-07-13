package com.example.demo.cryptologger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoLoggerRepository extends JpaRepository<CryptoLogger, Long> {
    CryptoLoggerRepository findById(String id);

    List<CryptoLogger> findAllBySymbol(String symbol);
}
