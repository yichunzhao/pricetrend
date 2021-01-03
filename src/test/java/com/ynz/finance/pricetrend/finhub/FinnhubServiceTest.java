package com.ynz.finance.pricetrend.finhub;

import com.ynz.finance.pricetrend.domain.StockSymbol;
import com.ynz.finance.pricetrend.domain.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Slf4j
class FinnhubServiceTest {
    @Autowired
    private FinnhubService finnhubService;

    @Test
    @DisplayName("Get all US stock symbols in a list")
    void testGetAllUSStockSymbolInList() {
        List<StockSymbol> stockSymbols = finnhubService.getAllUSStockSymbol();
        assertThat(stockSymbols, not(empty()));
        log.info("the size of US stock symbols: " + stockSymbols.size());

        stockSymbols.stream().filter(s -> s.getSymbol().equals("TSLA")).findFirst().ifPresent(stockSymbol -> {
            assertAll(
                    () -> assertThat(stockSymbol.getSymbol(), is(equalTo("TSLA"))),
                    () -> assertThat(stockSymbol.getCurrency(), is("USD")),
                    () -> assertThat(stockSymbol.getDisplaySymbol(), is("TSLA")),
                    () -> assertThat(stockSymbol.getType(), is("EQS")),
                    () -> assertThat(stockSymbol.getDescription(), is("TESLA INC"))
            );
        });
    }

    @Test
    @DisplayName("Get All US in Symbol-StockSymbol map")
    void testGetAllUSStockSymbols() {
        Map<Symbol, StockSymbol> symbolMap = finnhubService.getUSSymbolStockMap();
        List<StockSymbol> stockSymbolList = finnhubService.getAllUSStockSymbol();
        assertThat(symbolMap.size(), is(equalTo(stockSymbolList.size())));
    }

    @Test
    @DisplayName("Get Nasdaq stock tickers")
    void testGetNasdaqStockSymbols() {
        Map<Symbol, StockSymbol> symbolMap = finnhubService.getAllNasdaqStocks();
        assertThat(symbolMap.size(), is(greaterThan(100)));
        System.out.println("symbol size: " + symbolMap.size());
    }

}