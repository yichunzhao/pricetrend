package com.ynz.finance.pricetrend.dao;

import com.ynz.finance.pricetrend.domain.Price;
import com.ynz.finance.pricetrend.domain.Symbol;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FianceAPI {
    Price getPriceBySymbol(Symbol symbol, LocalDate date) throws IOException;

    Map<Symbol, Price> getPricesBySymbols(List<Symbol> symbols, LocalDate date) throws IOException;
}
