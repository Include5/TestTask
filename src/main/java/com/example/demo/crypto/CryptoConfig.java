package com.example.demo.crypto;

import com.example.demo.cryptologger.CryptoLogger;
import com.example.demo.cryptologger.CryptoLoggerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableScheduling
@Slf4j
public class CryptoConfig {
    private CryptoRepository cryptoRepository;
    private CryptoLoggerRepository cryptoLoggerRepository;

    @Autowired
    public CryptoConfig(CryptoRepository cryptoRepository, CryptoLoggerRepository cryptoLoggerRepository) {
        this.cryptoRepository = cryptoRepository;
        this.cryptoLoggerRepository = cryptoLoggerRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(CryptoRepository cryptoRepository) {
        return args -> {
            // Предопределенный список доступных криптовалют
            Crypto btc = new Crypto("90", "BTC");
            Crypto eth = new Crypto("80", "ETH");
            Crypto sol = new Crypto("48543", "SOL");
            cryptoRepository.saveAll(List.of(btc, eth, sol));
        };
    }

    @Scheduled(fixedRate  = 10000)
    @Transactional
    public void updateCryptoPriceEveryMinute() {
        RestTemplate restTemplate = new RestTemplate();
        List fetchedData = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=90,80,48543", Object[].class)));

        String[] currencies = new String[] {"BTC", "ETH", "SOL"};

        for (int i = 0; i < 3; i++) {
            LinkedHashMap<String, String> currency = (LinkedHashMap<String, String>) fetchedData.get(i);
            String price = currency.get("price_usd");

            List<CryptoLogger> cryptoLoggerList = this.cryptoLoggerRepository.findAllBySymbol(currencies[i]);

            cryptoLoggerList.forEach(cryptoLogger -> {
                // Цена с момента регистрации
                Double savedPrice = Double.parseDouble(cryptoLogger.getPrice_usd());

                // Актуальная цена
                Double relevantPrice = Double.parseDouble(price);

                // Процент изменения цены
                Double percent = ((relevantPrice / savedPrice) * 100) - 100;

                if (percent > 1.0 ) {
                    log.warn(cryptoLogger.getSymbol() + " - " + cryptoLogger.getUsername() + " - " + String.format("%.2f", percent) + "%");
                }
            });

            // Обновление цены криптовалюты в таблице
            this.cryptoRepository.updateCrypto((long) i + 1, price);
        }


    }


}
