package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;
import yahoofinance.Stock;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface StockServices {
    List<Stock> calMaxPriceGrowthStock(Set<Symbol> symbols, int limit);

    List<Stock> calMaxPriceGrowthStock(Set<Symbol> symbols, LocalDate from, int limit);
}
