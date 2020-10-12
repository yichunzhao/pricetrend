package com.ynz.finance.pricetrend.dao;

import com.ynz.finance.pricetrend.domain.Price;
import com.ynz.finance.pricetrend.domain.Symbol;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class YahooFinanceApis implements FianceAPI {

    @Override
    public Price getPriceBySymbol(Symbol symbol, LocalDate date) throws IOException {
        Stock stock = YahooFinance.get(symbol.getName(), convert(date));

        return Price.of(stock.getQuote().getPreviousClose());
    }

    @Override
    public Map<Symbol, Price> getPricesBySymbols(List<Symbol> symbols, LocalDate date) throws IOException {
        String[] symbolArray = symbols.stream().map(Symbol::getName).toArray(String[]::new);

        return YahooFinance.get(symbolArray, convert(date))
                .entrySet()
                .stream()
                .collect(
                        toMap(entry -> Symbol.of(entry.getKey()),
                                entry -> Price.of(entry.getValue().getQuote().getPrice()), (o, n) -> n
                        )
                );

    }

    Calendar convert(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        return calendar;
    }
}
