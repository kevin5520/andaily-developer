package com.andaily.web.util;

import com.andaily.domain.shared.DateUtils;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Shengzhao Li
 */
public class DateLocaleTest {

    @Test
    public void locale() {

        final TimeZone asiaTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        final TimeZone europeTimeZone = TimeZone.getTimeZone("Europe/Dublin");
//        TimeZone.setDefault(asiaTimeZone);

        GregorianCalendar chinaCalendar = new GregorianCalendar(asiaTimeZone);
        Date china = chinaCalendar.getTime();
        System.out.println(DateUtils.toDateTime(china));

        GregorianCalendar englishCalendar = new GregorianCalendar(europeTimeZone);
        Date europe = englishCalendar.getTime();
        System.out.println(DateUtils.toDateTime(europe));

        final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DEFAULT_DATE_TIME_FORMAT);
        dateFormat.setTimeZone(europeTimeZone);
        final String format = dateFormat.format(new Date());
        System.out.println("DateFormat:  " + format);


        System.out.println(TimeZone.getDefault());
    }
}
