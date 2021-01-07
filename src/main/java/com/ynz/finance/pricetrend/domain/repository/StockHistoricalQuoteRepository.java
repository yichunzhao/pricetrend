package com.ynz.finance.pricetrend.domain.repository;

import com.ynz.finance.pricetrend.domain.yahoo.StockHistoricalQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockHistoricalQuoteRepository extends JpaRepository<StockHistoricalQuote, Integer> {
    List<StockHistoricalQuote> findBySymbol(String symbol);
}
