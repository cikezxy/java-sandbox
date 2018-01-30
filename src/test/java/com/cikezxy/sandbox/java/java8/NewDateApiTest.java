package com.cikezxy.sandbox.java.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewDateApiTest {

    @Test
    public void testLocalDate() {
        LocalDate date = LocalDate.of(2012, 3, 18);
        assertEquals(2012, date.getYear());
        assertEquals(3, date.getMonthValue());
        assertEquals(18, date.getDayOfMonth());
        assertTrue(date.isLeapYear());
        assertEquals(31, date.lengthOfMonth());
        System.out.println(LocalDate.now());
    }

    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.of(13, 45, 20);
        assertEquals(13, time.getHour());
        assertEquals(45, time.getMinute());
        assertEquals(20, time.getSecond());
        System.out.println(LocalTime.now());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);

        LocalDateTime dt2 = dt1.withYear(2011);
        assertEquals(2011, dt2.getYear());

        LocalDateTime dt3 = dt1.minusMonths(1);
        assertEquals(2, dt3.getMonthValue());

        LocalDateTime dt4 = dt1.with(TemporalAdjusters.firstDayOfNextYear());
        assertEquals(2015, dt4.getYear());
        assertEquals(1, dt4.getMonthValue());
        assertEquals(1, dt4.getDayOfMonth());

        System.out.println(dt1);
    }

    @Test
    public void testInstant() {
        Instant.now();
    }

    @Test
    public void testDuration() {
        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println(threeMinutes.getSeconds());
    }

    @Test
    public void testPeriod() {
        Period period = Period.ofDays(10);
        System.out.println(period.getDays());
    }

    @Test
    public void testFormatter() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(dt1.format(DateTimeFormatter.BASIC_ISO_DATE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(dt1.format(formatter));
    }
}
