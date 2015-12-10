package com.oskm.time;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class TimeGeneratorTest {

    private static final Logger LOG = Logger.getLogger(TimeGeneratorTest.class);

    @Before
    public void before() {

    }

    @Test
    public void shouldGetAfterOneDay() {

        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(utc);

        calendar.set(1582, Calendar.OCTOBER, 4);

        String pattern = "yyyy.MM.dd";

        String theDay = toString(calendar, pattern, utc);

        assertEquals(theDay, "1582.10.04");

        calendar.add(Calendar.DATE, 1);
        String nextDay = toString(calendar, pattern, utc);

        assertEquals(nextDay, "1582.10.15");
    }

    @Test
    public void doesSystemCurrentTimeMillisReturnUTC() {

        long utcBySystem = System.currentTimeMillis();
        long utcByCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
        long utcByCalendarGMT = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();

        LOG.debug(utcBySystem);
        LOG.debug(utcByCalendar);
        LOG.debug(utcByCalendarGMT);

        assertEquals(utcBySystem, utcByCalendar);

    }

    private String toString(Calendar calendar, String pattern, TimeZone zone) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(zone);
        return format.format(calendar.getTime());
    }
}
