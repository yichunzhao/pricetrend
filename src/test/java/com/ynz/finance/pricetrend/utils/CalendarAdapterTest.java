package com.ynz.finance.pricetrend.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

class CalendarAdapterTest {

    @Test
    void testCreatingCalendarAdapter() {
        int expectedYear = 2020;
        int expectedMonth = 10;
        int expectedDay = 10;

        Calendar converted = LocalDateAdapter.of(LocalDate.of(expectedYear, expectedMonth, expectedDay)).getCalendar();
        assertAll(
                () -> assertThat(converted, notNullValue()),
                () -> assertThat(converted, is(instanceOf(Calendar.class))),
                () -> assertThat(converted.get(Calendar.YEAR), is(expectedYear)),
                () -> assertThat(converted.get(Calendar.MONTH), is(expectedMonth)),
                () -> assertThat(converted.get(Calendar.DAY_OF_MONTH), is(expectedDay))
        );

    }

}