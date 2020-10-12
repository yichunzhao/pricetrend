package com.ynz.finance.pricetrend.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class Symbol {
    private final String name;
}
