package com.ynz.finance.pricetrend.utils;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@RequiredArgsConstructor(staticName = "of")
public class LocalDateAdapter implements AsCalendar {
    private final LocalDate localDate;

    @Override
    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        return calendar;
    }
}
