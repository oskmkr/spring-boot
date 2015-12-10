/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4.parser;

import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ToStringResponseParser implements HttpResponseParser<String> {
    private static final String DEFAULT_CHARSET = "utf-8";
    private String charset = DEFAULT_CHARSET;

    public String parse(InputStream responseBody) throws HttpResponseParseException {
        try {
            return iOUtilsToString(responseBody);
        } catch (IOException e) {
            throw new HttpResponseParseException(e.getMessage(), e);
        }
    }

    protected String iOUtilsToString(InputStream responseBody) throws IOException {
        return IOUtils.toString(responseBody, charset);
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
