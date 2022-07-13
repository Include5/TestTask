package com.example.demo.cryptologger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/api/cryptologger")
public class CryptoLoggerController {

    private final CryptoLoggerService cryptoLoggerService;

    @Autowired
    public CryptoLoggerController(CryptoLoggerService cryptoLoggerService) {
        this.cryptoLoggerService = cryptoLoggerService;
    }


    @PostMapping()
    public CryptoLogger notify(@RequestParam("username") String username, @RequestParam("symbol") String symbol) {
        return this.cryptoLoggerService.notify(username, symbol);
    }

}
