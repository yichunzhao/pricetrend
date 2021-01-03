package com.ynz.finance.pricetrend.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

class LocalDateAdapterTest {
    int year = 2020;
    int month = 10;
    int day = 10;

    @Test
    void toCalendar() {
        Calendar converted = LocalDateAdapter.of(LocalDate.of(year, month, day)).toCalendar();

        assertAll(
                () -> assertThat(converted.get(Calendar.YEAR), is(year)),
                () -> assertThat(converted.get(Calendar.MONTH), is(Calendar.OCTOBER)),
                () -> assertThat(converted.get(Calendar.DAY_OF_MONTH), is(day))
        );
    }

    @Test
    void of() {
        assertThat(LocalDateAdapter.of(LocalDate.of(year, month, day)), is(instanceOf(LocalDateAdapter.class)));
    }

    //LocalDate.of(2019, 12, 31)

    @Test
    @Disabled
    void LocalDateToCalendar(){
        Calendar converted = LocalDateAdapter.of(LocalDate.of(2019, 12, 31)).toCalendar();
        assertAll(
                () -> assertThat(converted.get(Calendar.YEAR), is(2019)),
                () -> assertThat(converted.get(Calendar.MONTH), is(Calendar.DECEMBER)),
                () -> assertThat(converted.get(Calendar.DAY_OF_MONTH), is(31))
        );

    }
}