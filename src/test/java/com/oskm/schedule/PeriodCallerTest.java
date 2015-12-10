package com.oskm.schedule;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-schedule.xml"})
public class PeriodCallerTest {

    private static final Logger LOG = Logger.getLogger(PeriodCallerTest.class);

    @Test
    public void test() {
        LOG.debug("start...");
    }
}
