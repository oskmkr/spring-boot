/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HmacSha1Test {

    private static final Logger LOG = LoggerFactory.getLogger(HmacSha1Test.class);

    private HmacSha1 hmacSha1 = new HmacSha1();

    @Test
    public void encrypt() throws UnsupportedEncodingException {
        String data = "ifttt";
        String key = "smarthome_extension";

        String actual = hmacSha1.encrypt(data, key);

        LOG.debug("encryption result : " + actual);
        assertThat(actual, is("sFWTyIjCjO6MWZlzGsu3V4j232k="));
    }
}