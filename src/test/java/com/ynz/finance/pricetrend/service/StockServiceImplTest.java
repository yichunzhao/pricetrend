package com.ynz.finance.pricetrend.service;

import com.ynz.finance.pricetrend.domain.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@Slf4j
class StockServiceImplTest {
    @Autowired
    private StockServices stockServices;

    @Test
    void calMaxPriceGrowthStock() throws IOException {
        List<Map.Entry<Symbol, BigDecimal>> result = stockServices.calUSStockMaxPriceGrowth(10);
        assertThat(result, is(notNullValue()));
        log.info(result.toString()
        );
    }
}