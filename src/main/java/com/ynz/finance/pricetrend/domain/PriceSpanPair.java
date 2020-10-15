package com.ynz.finance.pricetrend.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class PriceSpanPair {
    private final Price start;
    private final Price end;
}
