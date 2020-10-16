package com.ynz.finance.pricetrend.utils;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@RequiredArgsConstructor(staticName = "of")
public class CalendarAdapter implements AsLocalDate {
    private final Calendar calendar;

    @Override
    public LocalDate toLocalDate() {
        if (calendar == null) throw new IllegalArgumentException("calendar should not be a null value");
        return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
}
