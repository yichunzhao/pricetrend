package com.ynz.finance.pricetrend.domain.repository;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class NasdaqStocksRepositoryTest {
    @Autowired
    private NasdaqStocksRepository stocksRepository;

    @Test
    void testSaveStock() {
        NasdaqStock stock = new NasdaqStock();
        NasdaqStock found = stocksRepository.save(stock);
        assertThat(found.getId(), is(notNullValue()));
    }

}