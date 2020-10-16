package com.ynz.finance.pricetrend.yahoofinance;

import com.ynz.finance.pricetrend.domain.Price;
import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.Symbol;
import com.ynz.finance.pricetrend.utils.CalendarAdapter;
import com.ynz.finance.pricetrend.utils.LocalDateAdapter;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class YahooFinanceApis implements FianceAPI {

    @Override
    public PriceSpanPair getPriceBySymbol(Symbol symbol, LocalDate date) throws IOException {
        Stock stock = YahooFinance.get(symbol.getName(), LocalDateAdapter.of(date).toCalendar(), Interval.DAILY);
        return getPriceSpanPair(stock);
    }

    private PriceSpanPair getPriceSpanPair(Stock stock) {
        if (stock == null) throw new IllegalStateException("there is no Stock for this date");

        List<HistoricalQuote> historicalQuotes = null;
        try {
            historicalQuotes = stock.getHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (historicalQuotes != null && !historicalQuotes.isEmpty()) {
            HistoricalQuote start = historicalQuotes.get(0);
            HistoricalQuote end = historicalQuotes.get(historicalQuotes.size() - 1);

            Price pStart = Price.of(start.getAdjClose(), CalendarAdapter.of(start.getDate()).toLocalDate());
            Price pEnd = Price.of(end.getAdjClose(), CalendarAdapter.of(end.getDate()).toLocalDate());

            return PriceSpanPair.of(pStart, pEnd);
        }

        return PriceSpanPair.of(null, null);
    }

    @Override
    public Map<Symbol, PriceSpanPair> getPricesBySymbols(List<Symbol> symbols, LocalDate date) throws IOException {
        String[] symbolArray = symbols.stream().map(Symbol::getName).toArray(String[]::new);

        return YahooFinance.get(symbolArray, LocalDateAdapter.of(date).toCalendar())
                .entrySet()
                .stream()
                .collect(toMap(entry -> Symbol.of(entry.getKey()),
                        entry -> getPriceSpanPair(entry.getValue()),
                        (o, n) -> n)
                );
    }

}
