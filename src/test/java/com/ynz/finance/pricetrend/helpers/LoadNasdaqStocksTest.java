package com.ynz.finance.pricetrend.helpers;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoadNasdaqStocksTest {

    private final LoadNasdaqStocks loadNasdaqStocks = new LoadNasdaqStocks();

    @Test
    void testLoadStocksIntoList() {
        List<NasdaqStock> stocks = loadNasdaqStocks.doAction();

        assertAll(
                () -> assertThat(stocks.size(), is(greaterThan(3000))),
                () -> assertThat(stocks.size(), is(lessThan(4000))),
                () -> assertThat(stocks.get(0).getSymbol(), is("AACG")),
                () -> assertThat(stocks.get(1).getSymbol(), is("AACQ")),
                () -> assertThat(stocks.get(stocks.size() - 1).getSymbol(), is("ZYXI"))
        );
    }

}