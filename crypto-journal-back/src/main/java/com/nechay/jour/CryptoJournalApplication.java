package com.nechay.jour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author anechaev
 * @since 04.05.2024
 */
@SpringBootApplication
public class CryptoJournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoJournalApplication.class, args);
    }
}
