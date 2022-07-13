package com.example.demo.cryptologger;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class CryptoLogger {

    @Id
    @SequenceGenerator(
            name = "cryptologger_sequence",
            sequenceName = "cryptologger_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cryptologger_sequence"
    )
    private Long id;
    private String username;
    private String symbol;
    private String price_usd;


    public CryptoLogger() {
    }

    public CryptoLogger(String username, String symbol, String price_usd) {
        this.username = username;
        this.symbol = symbol;
        this.price_usd = price_usd;
    }
}
