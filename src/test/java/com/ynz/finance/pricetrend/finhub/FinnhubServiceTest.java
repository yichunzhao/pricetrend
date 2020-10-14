package com.ynz.finance.pricetrend.finhub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@SpringBootTest
class FinnhubServiceTest {
    @Autowired
    private FinnhubService finnhubService;

    @Test
    void getUSStockSymbol() {
        List<StockSymbol> stockSymbols = finnhubService.getUSStockSymbol();
        assertThat(stockSymbols, not(empty()));
    }
}