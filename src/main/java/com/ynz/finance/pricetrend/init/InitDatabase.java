package com.ynz.finance.pricetrend.init;

import com.ynz.finance.pricetrend.domain.mapper.HistoricalQuoteMapper;
import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import com.ynz.finance.pricetrend.domain.repository.NasdaqStocksRepository;
import com.ynz.finance.pricetrend.domain.repository.StockHistoricalQuoteRepository;
import com.ynz.finance.pricetrend.helpers.LoadNasdaqStocks;
import com.ynz.finance.pricetrend.service.Cal233DayAveragePrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import yahoofinance.histquotes.HistoricalQuote;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InitDatabase implements CommandLineRunner {
    @Value("${app.database.init.stocks}")
    private boolean initStocks;

    @Value("${app.database.init.history}")
    private boolean initHistory;

    @Autowired
    private NasdaqStocksRepository stocksRepository;

    @Autowired
    public LoadNasdaqStocks loadNasdaqStocks;

    @Autowired
    private StockHistoricalQuoteRepository historicalQuoteRepository;

    @Autowired
    private Cal233DayAveragePrice cal233DayAveragePrice;

    @Override
    public void run(String... args) throws Exception {
        log.info("populating Nasdaq stocks into database");

        if (initStocks) {
            List<NasdaqStock> nasdaqStocksList = loadNasdaqStocks.doAction();
            stocksRepository.saveAll(nasdaqStocksList);
        }

        if (initHistory) {

            List<String> stocks = stocksRepository.findAllSymbols();

            for (String stock : stocks) {

                List<HistoricalQuote> historicalQuotes = cal233DayAveragePrice.getStockOneYearBefore(stock, LocalDate.of(2020, 12, 31));
                if (historicalQuotes != null) {
                    historicalQuoteRepository.saveAll(historicalQuotes.stream()
                            .map(h -> HistoricalQuoteMapper.toEntity(h)).collect(Collectors.toList()));
                } else {
                    //data is not enough
                    stocksRepository.updateDataNotEnough(stock, true);
                }
            }
        }
    }

}
