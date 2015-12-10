package com.oskm.support.remote.httpclient;

@SuppressWarnings("serial")
public class HttpClientExecutionFailException extends HttpClientException {

    public HttpClientExecutionFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientExecutionFailException(String message) {
        super(message);
    }

    public HttpClientExecutionFailException(int statusCode, String url) {
        super("http client execution failed. status code [" + statusCode + "] url template:" + url);
    }
}
