package com.oskm.support.remote.httpclient;

@SuppressWarnings("serial")
public class HttpClientException extends RuntimeException {

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(String message) {
        super(message);
    }
}
