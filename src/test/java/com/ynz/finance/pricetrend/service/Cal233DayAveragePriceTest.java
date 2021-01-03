package com.ynz.finance.pricetrend.service;

import org.junit.jupiter.api.Test;
import yahoofinance.histquotes.HistoricalQuote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Cal233DayAveragePriceTest {

    private Cal233DayAveragePrice averagePrice = new Cal233DayAveragePrice();

    @Test
    void getStockOneYearBefore() {

        List<HistoricalQuote> historicalQuotes = averagePrice.getStockOneYearBefore("tdy", LocalDate.of(2020, 12, 31));
        assertThat(historicalQuotes.size(), is(254));
    }

    @Test
    void calc20DaysPre233DaysPriceAverage() {
        List<HistoricalQuote> historicalQuotes = averagePrice.getStockOneYearBefore("tdy", LocalDate.of(2020, 12, 31));
        Map<HistoricalQuote, Double> averagePrices = averagePrice.cal20Days233Average(historicalQuotes);
        assertThat(averagePrices.size(), is(20));
    }

    @Test
    void determine20Days233AverageIncrementalOrNot() {
        assertTrue(averagePrice.isIncremental("tdy", LocalDate.of(2020, 12, 31)));
    }

    @Test
    void determineDataSetFirstDay() {
        LocalDate firstDay = averagePrice.determineFirstDayOfDataSet(LocalDate.of(2020, 12, 31));
        assertThat(firstDay, is(LocalDate.of(2019, 12, 31)));
    }

}