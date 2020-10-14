package com.ynz.finance.pricetrend.finhub;

import lombok.Data;

@Data
public class StockSymbol {
    private String description;
    private String displaySymbol;
    private String symbol;
    private String type;
    private String currency;
}
