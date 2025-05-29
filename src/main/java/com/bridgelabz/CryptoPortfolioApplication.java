package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoPortfolioApplication.class, args);
        System.out.println("Hi this is a Crypto Portfolio Tracker");
    }

}
