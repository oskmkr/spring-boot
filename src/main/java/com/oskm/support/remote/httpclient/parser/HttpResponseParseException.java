package com.oskm.support.remote.httpclient.parser;

import com.oskm.support.remote.httpclient.HttpClientException;

@SuppressWarnings("serial")
public class HttpResponseParseException extends HttpClientException {

    public HttpResponseParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpResponseParseException(String message) {
        super(message);
    }
}
