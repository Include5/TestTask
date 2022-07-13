package com.example.demo.crypto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Crypto {

    @Id
    @SequenceGenerator(
            name = "crypto_sequence",
            sequenceName = "crypto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "crypto_sequence"
    )
    private Long _id;
    private String id;
    private String symbol;
    private String price_usd;


    public Crypto() {
    }

    public Crypto(String id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }
}
