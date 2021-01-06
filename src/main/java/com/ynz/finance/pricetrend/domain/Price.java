package com.ynz.finance.pricetrend.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data(staticConstructor = "of")
public class Price {
    private final BigDecimal stockPrice;
    private final LocalDate date;
}
