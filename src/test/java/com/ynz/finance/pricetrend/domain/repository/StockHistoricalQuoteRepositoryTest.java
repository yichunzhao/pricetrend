package com.ynz.finance.pricetrend.domain.repository;

import com.ynz.finance.pricetrend.domain.mapper.HistoricalQuoteMapper;
import com.ynz.finance.pricetrend.domain.yahoo.StockHistoricalQuote;
import com.ynz.finance.pricetrend.service.Cal233DayAveragePrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import yahoofinance.histquotes.HistoricalQuote;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class StockHistoricalQuoteRepositoryTest {
    @Autowired
    private StockHistoricalQuoteRepository quoteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Cal233DayAveragePrice averagePrice = new Cal233DayAveragePrice();

    @Test
    void saveStockHistoricalQuote() {
        List<HistoricalQuote> historicalQuotes = averagePrice.getStockOneYearBefore("tdy", LocalDate.of(2020, 12, 31));
        assertThat(historicalQuotes.size(), is(greaterThan(253)));

        List<StockHistoricalQuote> stockHistoricalQuotes = historicalQuotes.stream()
                .map(x -> HistoricalQuoteMapper.toEntity(x)).collect(Collectors.toList());

        List<StockHistoricalQuote> saved = quoteRepository.saveAll(stockHistoricalQuotes);
        quoteRepository.flush();

        assertThat(saved, hasSize(historicalQuotes.size()));
    }

    @Test
    void findBySymbol() {
        StockHistoricalQuote quote = new StockHistoricalQuote();
        quote.setSymbol("tdy");

        testEntityManager.persistAndFlush(quote);

        List<StockHistoricalQuote> quotes = quoteRepository.findBySymbol("tdy");
        assertThat(quotes, hasSize(1));
    }
}