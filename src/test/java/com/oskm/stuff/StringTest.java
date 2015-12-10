package com.oskm.stuff;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by oskm on 2014-09-22.
 */
public class StringTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringTest.class);

    /**
     * %04d 의 의미
     * % -  명령의시작
     * 0 - 채워질 문자
     * 4 - 총 자리수
     * d - 십진정수
     */
    @Test
    public void NumericFormat() {
        int value = 1000;

        String formattedValue = String.format("%05d", value);

        LOG.debug(formattedValue);
    }

    @Test
    public void NumericFormat_Float() {
        float value = 0.00f;

        String formattedValue = String.format("%.2f", value);

        LOG.debug(formattedValue);
    }
}
