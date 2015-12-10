package com.oskm.support.remote.httpclient4;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class HttpComponentClientTemplateTest {

    @Before
    public void before() {
    }

    @Test
    public void requestGetMethod() {

        client.execute();

    }

    @Autowired
    @Qualifier("tmpHttpComponentClientTemplate")
    private HttpComponentClientTemplate<String> client;


}
