package com.example.demo.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public List<Crypto> findAll() {
        return this.cryptoRepository.findAll();
    }

    public Crypto findOne(String id) {
        return this.cryptoRepository.findById(id);
    }
}
