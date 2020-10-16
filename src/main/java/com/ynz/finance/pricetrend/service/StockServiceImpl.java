package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.Symbol;
import com.ynz.finance.pricetrend.finhub.Finnhub;
import com.ynz.finance.pricetrend.finhub.StockSymbol;
import com.ynz.finance.pricetrend.yahoofinance.FianceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockServices {
    protected final Finnhub finnhub;
    protected final FianceAPI yahooFinanceApis;

    @Override
    public Map<BigDecimal, Symbol> calMaxPriceGrowthStock(Set<Symbol> symbols, int limit) throws IOException {
        Map<Symbol, StockSymbol> stockSymbolMap = finnhub.getUSSymbolStockMap();
        Set<Symbol> symbolSet = stockSymbolMap.keySet();

        LocalDate from = LocalDate.now().minusDays(6L);
        Map<Symbol, PriceSpanPair> symbolPriceSpanPairMap = yahooFinanceApis.getPricesBySymbols(symbolSet, from);

        NavigableMap<BigDecimal, Symbol> symbolBigDecimalMap = symbolPriceSpanPairMap.entrySet()
                .stream()
                .collect(Collectors.toMap(s -> calGrowth(s.getValue()), s -> s.getKey(), (o, n) -> n, TreeMap::new));
        return symbolBigDecimalMap.descendingMap().entrySet().stream().limit(10).collect(Collectors.toMap(s -> s.getKey(), s -> s.getValue()));
    }

    private BigDecimal calGrowth(PriceSpanPair pair) {
        return pair.getEnd().getStockPrice().subtract(pair.getStart().getStockPrice());
    }

    @Override
    public Map<BigDecimal, Symbol> calMaxPriceGrowthStock(Set<Symbol> symbols, LocalDate from, int limit) {
        return null;
    }
}
