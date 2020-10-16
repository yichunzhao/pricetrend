package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface StockServices {
    Map<BigDecimal, Symbol> calMaxPriceGrowthStock(Set<Symbol> symbols, int limit) throws IOException;

    Map<BigDecimal, Symbol> calMaxPriceGrowthStock(Set<Symbol> symbols, LocalDate from, int limit);
}
