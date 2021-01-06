package com.ynz.finance.pricetrend.init;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import com.ynz.finance.pricetrend.domain.repository.NasdaqStocksRepository;
import com.ynz.finance.pricetrend.helpers.LoadNasdaqStocks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InitDatabase implements CommandLineRunner {
    @Autowired
    private NasdaqStocksRepository stocksRepository;

    @Autowired
    public LoadNasdaqStocks loadNasdaqStocks;

    @Override
    public void run(String... args) throws Exception {
        log.info("populating Nasdaq stocks into database");

        List<NasdaqStock> nasdaqStocksList = loadNasdaqStocks.doAction();
        stocksRepository.saveAll(nasdaqStocksList);
    }

}
