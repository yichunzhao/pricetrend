package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.utils.LocalDateAdapter;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Cal233DayAveragePrice {
    private static final int preCount = 20 + 1;
    private static final int dayOfAverage = 233;

    public List<HistoricalQuote> getStockOneYearBefore(String stock, LocalDate startDate) {
        DayOfWeek dayOfWeek = startDate.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new IllegalArgumentException("weekend is not valid reference day.");
        }

        List<HistoricalQuote> historicalQuotes = null;

        try {
            Stock found = YahooFinance.get(stock);
            Calendar calendar = LocalDateAdapter.of(startDate.minus(15, ChronoUnit.MONTHS)).toCalendar();
            historicalQuotes = found.getHistory(calendar, Interval.DAILY);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalQuotes;
    }

    public List<HistoricalQuote> get20DaysBeforeLastDay(List<HistoricalQuote> historicalQuotes) {
        int size = historicalQuotes.size();
        return historicalQuotes.subList(size - 21, size - 1);
    }

    public HistoricalQuote getLastDay(List<HistoricalQuote> historicalQuotes) {
        return historicalQuotes.get(historicalQuotes.size());
    }

    public Map<HistoricalQuote, Double> cal20Days233Average(List<HistoricalQuote> historicalQuotes) {
        Map<HistoricalQuote, Double> average233 = new LinkedHashMap<>();

        int size = historicalQuotes.size();

        //20 days before last day
        List<HistoricalQuote> preLast20Days = get20DaysBeforeLastDay(historicalQuotes);

        for (int i = size - preCount; i < size - 1; i++) {
            double avg = historicalQuotes.subList(i - dayOfAverage, i - 1).stream()
                    .map(HistoricalQuote::getAdjClose)
                    .collect(Collectors.averagingDouble(BigDecimal::doubleValue));

            average233.put(historicalQuotes.get(i), avg);
        }

        return average233;
    }

    public boolean isIncremental(String stock, LocalDate startDate) {

        List<HistoricalQuote> historicalQuotes = getStockOneYearBefore(stock, startDate);
        Map<HistoricalQuote, Double> average20Days = cal20Days233Average(historicalQuotes);

        List<Double> averages = average20Days.values().stream().collect(Collectors.toList());
        int size = averages.size();
        return averages.get(0) < averages.get(size - 1);
    }


}
