package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StockServices {
    List<Map.Entry<Symbol, BigDecimal>> calUSStockMaxPriceGrowth(int limit) throws IOException;

    List<Map.Entry<Symbol, BigDecimal>> calUSStockMaxPriceGrowth(LocalDate from, int limit);
}
