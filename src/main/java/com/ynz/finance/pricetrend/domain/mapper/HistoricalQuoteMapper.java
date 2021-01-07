package com.ynz.finance.pricetrend.domain.mapper;

import com.ynz.finance.pricetrend.domain.yahoo.StockHistoricalQuote;
import yahoofinance.histquotes.HistoricalQuote;

public class HistoricalQuoteMapper {

    public static StockHistoricalQuote toEntity(HistoricalQuote historicalQuote) {
        StockHistoricalQuote stockHistoricalQuote = new StockHistoricalQuote();
        stockHistoricalQuote.setSymbol(historicalQuote.getSymbol());
        stockHistoricalQuote.setAdjClose(historicalQuote.getAdjClose());
        stockHistoricalQuote.setDate(historicalQuote.getDate());
        stockHistoricalQuote.setClose(historicalQuote.getClose());
        stockHistoricalQuote.setHigh(historicalQuote.getHigh());
        stockHistoricalQuote.setLow(historicalQuote.getLow());
        stockHistoricalQuote.setOpen(historicalQuote.getOpen());
        stockHistoricalQuote.setVolume(historicalQuote.getVolume());

        return stockHistoricalQuote;
    }

}
