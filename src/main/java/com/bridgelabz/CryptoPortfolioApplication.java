package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bridgelabz.repository")
public class CryptoPortfolioApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoPortfolioApplication.class, args);
    }
}
