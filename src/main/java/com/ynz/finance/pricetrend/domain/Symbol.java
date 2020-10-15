package com.ynz.finance.pricetrend.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class Symbol implements Comparable {
    private final String name;

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Symbol)) throw new IllegalArgumentException("o is not Symbol instance");

        return this.name.compareTo(((Symbol)o).getName());
    }
}
