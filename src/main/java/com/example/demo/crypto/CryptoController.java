package com.example.demo.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    // Просмотр списка доступных криптовалют
    @GetMapping
    public List<Crypto> getAll() {
        return this.cryptoService.findAll();
    }

    // Просмотр актуальной цены для указаной криптовалюты
    @GetMapping("{id}")
    public String getCryptoPrice(@PathVariable String id) {
        return this.cryptoService.findOne(id).getPrice_usd();
    }

}
