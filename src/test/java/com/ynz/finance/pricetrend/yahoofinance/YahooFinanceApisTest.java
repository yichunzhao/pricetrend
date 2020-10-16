package com.ynz.finance.pricetrend.yahoofinance;

import com.ynz.finance.pricetrend.domain.PriceSpanPair;
import com.ynz.finance.pricetrend.domain.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Slf4j
class YahooFinanceApisTest {
    @Autowired
    private YahooFinanceApis financeApis;

    @Test
    void testGetPriceBySymbol() throws IOException {
        Symbol tesla = Symbol.of("TSLA");
        LocalDate earlyDate = LocalDate.now().minusMonths(6L);

        PriceSpanPair price = financeApis.getPriceBySymbol(tesla, earlyDate);
        log.info(price.toString());

        assertAll(
                () -> assertThat(price, is(notNullValue())),
                () -> assertThat(price.getStart(), is(notNullValue())),
                () -> assertThat(price.getEnd(), is(notNullValue()))
        );
    }

    @Test
    void whenGetPriceToday_ItReturnsPriceSpanWithNullValues() throws IOException {
        Symbol tesla = Symbol.of("TSLA");
        PriceSpanPair price = financeApis.getPriceBySymbol(tesla, LocalDate.now());

        assertAll(
                () -> assertThat(price.getStart(), is(nullValue())),
                () -> assertThat(price.getEnd(), is(nullValue()))
        );
    }

    @Test
    void testGetPricesBySymbols() throws IOException {
        Set<Symbol> symbolSet = new HashSet<>(Arrays.asList(Symbol.of("AAAU"), Symbol.of("AACG"), Symbol.of("AAA"), Symbol.of("AACQU")));

        Map<Symbol, PriceSpanPair> spanPairMap = financeApis.getPricesBySymbols(symbolSet, LocalDate.now().minusMonths(6L));

        assertAll(
                () -> assertThat(spanPairMap, is(notNullValue())),
                () -> assertThat(spanPairMap.size(), is(symbolSet.size())),
                () -> assertThat(spanPairMap.get(Symbol.of("AAAU")), is(instanceOf(PriceSpanPair.class))),
                () -> assertThat(spanPairMap.get(Symbol.of("AAAU")).getStart().getStockPrice(), is(notNullValue())),
                () -> assertThat(spanPairMap.get(Symbol.of("AAAU")).getEnd().getStockPrice(), is(notNullValue()))
        );


    }


}