package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;
import com.ynz.finance.pricetrend.finhub.Finnhub;
import com.ynz.finance.pricetrend.finhub.StockSymbol;
import com.ynz.finance.pricetrend.yahoofinance.FianceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockServices {
    protected final Finnhub finnhub;
    protected final FianceAPI yahooFinanceApis;

    @Override
    public List<Stock> calMaxPriceGrowthStock(Set<Symbol> symbols, int limit) {
        Map<Symbol, StockSymbol> stockSymbolMap = finnhub.getUSSymbolStockMap();
        Set<Symbol> symbolList = stockSymbolMap.keySet();

        return null;
    }

    @Override
    public List<Stock> calMaxPriceGrowthStock(Set<Symbol> symbols, LocalDate from, int limit) {
        return null;
    }
}
