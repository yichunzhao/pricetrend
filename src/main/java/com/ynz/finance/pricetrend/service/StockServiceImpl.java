package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.StockSymbol;
import com.ynz.finance.pricetrend.domain.Symbol;
import com.ynz.finance.pricetrend.finhub.Finnhub;
import com.ynz.finance.pricetrend.yahoofinance.FianceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockServices {
    protected final Finnhub finnhub;
    protected final FianceAPI yahooFinanceApis;

    @Override
    public List<Map.Entry<Symbol, BigDecimal>> calUSStockMaxPriceGrowth(int limit) throws IOException {
        Map<Symbol, StockSymbol> stockSymbolMap = finnhub.getUSSymbolStockMap();
        Set<Symbol> symbolSet = stockSymbolMap.keySet().stream().limit(100).collect(Collectors.toSet());

        LocalDate from = LocalDate.now().minusMonths(6L);

        //get prices from yahoo finance
        Map<Symbol, PriceSpanPair> symbolPriceSpanPairMap = yahooFinanceApis.getPricesBySymbols(symbolSet, from);

        Map<Symbol, BigDecimal> stockGrowthOverTime = symbolPriceSpanPairMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> calGrowth(entry.getValue())));

        Comparator<Map.Entry<Symbol, BigDecimal>> comparator = Map.Entry.comparingByValue();
        return stockGrowthOverTime.entrySet().stream().sorted(comparator.reversed()).limit(limit).collect(toList());
    }

    private BigDecimal calGrowth(PriceSpanPair pair) {
        return pair.getEnd().getStockPrice().subtract(pair.getStart().getStockPrice());
    }

    @Override
    public List<Map.Entry<Symbol, BigDecimal>> calUSStockMaxPriceGrowth(LocalDate from, int limit) {
        return null;
    }

}
