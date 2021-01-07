package com.ynz.finance.pricetrend.domain.nasdaq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NASDAQ_STOCKS")
@Getter
@Setter
public class NasdaqStock {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(unique = true)
    private String symbol;
    private String securityName;
    private String marketCategory;
    private String testIssue;
    private String financialStatus;
    private String roundLotSize;
    private String eTF;
    private String nextShares;

    private Boolean dataNotEnough;
    private Boolean is20Day233AvgIncremental;
}
