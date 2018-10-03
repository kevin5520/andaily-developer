package com.andaily.domain.shared;

import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

/**
 * Date: 13-8-28
 *
 * @author Shengzhao Li
 */
public class DateUtilsTest {

    @Test
    public void isToday() throws Exception {
        boolean today = DateUtils.isToday(DateUtils.now());
        assertTrue(today);

        today = DateUtils.isToday(DateUtils.getDate("2013-03-09"));
        assertFalse(today);
    }

    @Test
    public void isDate() throws Exception {
        boolean isDate = DateUtils.isDate(null);
        assertFalse(isDate);

        isDate = DateUtils.isDate("2013-09-11");
        assertTrue(isDate);

        isDate = DateUtils.isDate("20130911");
        assertFalse(isDate);
    }

    @Test
    public void plusDays() throws Exception {
        Date date = DateUtils.getDate("2014-06-011");
        Date newDate = DateUtils.plusDays(date, 3);
        assertEquals("2014-06-14", DateUtils.toDateText(newDate));

        newDate = DateUtils.plusDays(date, -3);
        assertEquals("2014-06-08", DateUtils.toDateText(newDate));

        newDate = DateUtils.plusDays(date, 30);
        assertEquals("2014-07-11", DateUtils.toDateText(newDate));
    }

    /**
     * OOps!  what happened, waiting................
     */
    @Test(enabled = false)
    public void periodAsDays() {
        Date start = DateUtils.now();
        Date end = DateUtils.now();

        long days = DateUtils.periodAsDays(start, end);
        System.out.println("Period: " + days);
        assertEquals(days, 0);

        start = DateUtils.getDate("2013-03-01 12:23:23", DateUtils.DEFAULT_DATE_TIME_FORMAT);
        end = DateUtils.getDate("2013-03-11 16:00:11", DateUtils.DEFAULT_DATE_TIME_FORMAT);
        System.out.println("Start [" + DateUtils.toDateTime(start) + "], End [" + DateUtils.toDateTime(end) + "]");

        days = DateUtils.periodAsDays(start, end);
        System.out.println("Period: " + days);
        assertEquals(days, 10);
    }
}
