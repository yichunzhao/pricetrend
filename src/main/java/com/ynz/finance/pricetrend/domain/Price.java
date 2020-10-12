package com.ynz.finance.pricetrend.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data(staticConstructor = "of")
public class Price {
    @Setter(AccessLevel.NONE)
    private final BigDecimal stockPrice;

}
