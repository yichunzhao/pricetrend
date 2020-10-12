package com.ynz.finance.pricetrend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Stock stock = YahooFinance.get("TSLA");

        log.info("Stock currency %s ", stock.getCurrency());
        log.info("Stock dividend %s ", stock.getDividend());
        System.out.printf("Stock currency %s \n ", stock.getCurrency());
        System.out.printf("Stock dividend %s \n ", stock.getDividend());
    }
}
