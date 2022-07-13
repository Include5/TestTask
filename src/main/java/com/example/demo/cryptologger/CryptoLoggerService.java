package com.example.demo.cryptologger;

import com.example.demo.crypto.Crypto;
import com.example.demo.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoLoggerService {

    private final CryptoLoggerRepository cryptoLoggerRepository;
    private final CryptoRepository cryptoRepository;

    @Autowired
    public CryptoLoggerService(CryptoLoggerRepository cryptoLoggerRepository, CryptoRepository cryptoRepository) {
        this.cryptoLoggerRepository = cryptoLoggerRepository;
        this.cryptoRepository = cryptoRepository;
    }

    public CryptoLogger notify(String username, String symbol) {
        Crypto crypto = this.cryptoRepository.findBySymbol(symbol);

        CryptoLogger cryptoLogger = new CryptoLogger(username, symbol, crypto.getPrice_usd());

        return this.cryptoLoggerRepository.save(cryptoLogger);
    }
}
