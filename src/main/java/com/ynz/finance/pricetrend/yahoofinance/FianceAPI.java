package com.ynz.finance.pricetrend.yahoofinance;

import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.Symbol;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface FianceAPI {
    //get a single stock price.
    PriceSpanPair getPriceBySymbol(Symbol symbol, LocalDate date) throws IOException;

    Map<Symbol, PriceSpanPair> getPricesBySymbols(Set<Symbol> symbols, LocalDate date) throws IOException;
}
