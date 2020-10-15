package com.ynz.finance.pricetrend.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data(staticConstructor = "of")
@Setter(AccessLevel.NONE)
public class Price {
    private final BigDecimal stockPrice;
    private final LocalDate date;
}
