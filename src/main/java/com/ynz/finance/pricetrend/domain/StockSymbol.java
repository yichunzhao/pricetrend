package com.ynz.finance.pricetrend.domain;

import lombok.Data;

@Data
public class StockSymbol {
    private String description;
    private String displaySymbol;
    private String symbol;
    private String type;
    private String currency;
    private String figi;
    private String mic;
}
