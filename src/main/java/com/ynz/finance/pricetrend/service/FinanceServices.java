package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;
import yahoofinance.Stock;

import java.util.List;

public interface FinanceServices {
    Symbol calMaxPriceIncrementalStock(List<Symbol> symbols);

    List<Stock> calMaxPriceIncrementalStock(List<Symbol> symbols, int limit);
}
