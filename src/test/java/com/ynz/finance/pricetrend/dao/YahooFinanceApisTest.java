package com.ynz.finance.pricetrend.dao;

import com.ynz.finance.pricetrend.domain.Price;
import com.ynz.finance.pricetrend.domain.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;


@SpringBootTest
@Slf4j
class YahooFinanceApisTest {
    @Autowired
    private YahooFinanceApis financeApis;

    @Test
    void testGetPriceBySymbol() throws IOException {
        Symbol tesla = Symbol.of("TSLA");
        LocalDate earlyDate = LocalDate.now().minusMonths(6L);

        Price price = financeApis.getPriceBySymbol(tesla, earlyDate);
        log.info(price.toString());


    }


}