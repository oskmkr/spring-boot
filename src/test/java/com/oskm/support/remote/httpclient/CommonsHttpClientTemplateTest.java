/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-external-httpclient.xml", "classpath:applicationContext-propertyplaceholder.xml"})
public class CommonsHttpClientTemplateTest {

    private Logger LOG = LoggerFactory.getLogger(CommonsHttpClientTemplateTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void execute() {
        HttpClientParameters params = new HttpClientParameters();

        long currentTime = System.currentTimeMillis();

        String actual = client.execute(params);
        LOG.debug("[result]", actual);

        LOG.info("[elapsed time] " + String.valueOf(System.currentTimeMillis() - currentTime));


    }

    @Autowired
    @Qualifier("naverMainCommonsHttpClientTemplate")
    private CommonsHttpClientTemplate<String> client;
}