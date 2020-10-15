package com.ynz.finance.pricetrend.yahoofinance;

import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.Symbol;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FianceAPI {
    //get a single stock price.
    PriceSpanPair getPriceBySymbol(Symbol symbol, LocalDate date) throws IOException;

    Map<Symbol, PriceSpanPair> getPricesBySymbols(List<Symbol> symbols, LocalDate date) throws IOException;
}
