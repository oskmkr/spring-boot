/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;

import java.io.IOException;
import java.util.Map;

/**
 * Created by sungkyu.eo on 2015-04-02.
 */
public interface HttpClientTemplate<T> {

    T execute(Map<String, String> parameters) throws HttpClientException, IOException;

    T execute() throws HttpClientException, IOException;

    T executeQuietly(HttpClientParams parameters) throws IOException;

    void setHttpResponseParser(HttpResponseParser<T> httpResponseParser);

}
