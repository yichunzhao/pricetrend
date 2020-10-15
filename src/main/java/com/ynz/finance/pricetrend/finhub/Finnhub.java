package com.ynz.finance.pricetrend.finhub;

import com.ynz.finance.pricetrend.domain.Symbol;

import java.util.Map;

public interface Finnhub {
    Map<Symbol, StockSymbol> getUSSymbolStockMap();
}
