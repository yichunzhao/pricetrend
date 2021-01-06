package com.ynz.finance.pricetrend.domain.repository;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasdaqStocksRepository extends JpaRepository<NasdaqStock, Integer> {
    @Query("select n.symbol from NasdaqStock n")
    List<String> findAllSymbols();
}
