package com.ynz.finance.pricetrend.domain.repository;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NasdaqStocksRepository extends JpaRepository<NasdaqStock, Integer> {
    @Query("select n.symbol from NasdaqStock n")
    List<String> findAllSymbols();

    @Modifying
    @Query("update NasdaqStock n set n.dataNotEnough = :status where n.symbol = :stockSymbol")
    @Transactional
    void updateDataNotEnough(@Param("stockSymbol") String stockSymbol, @Param("status") Boolean status);

    //is20Day233AvgIncremental
    @Modifying
    @Query("update NasdaqStock n set n.is20Day233AvgIncremental = :is20Day233AvgIncremental where n.symbol = :stockSymbol")
    @Transactional
    void updateIs20Day233AvgIncremental(@Param("stockSymbol") String stockSymbol, @Param("is20Day233AvgIncremental") Boolean status);

}
