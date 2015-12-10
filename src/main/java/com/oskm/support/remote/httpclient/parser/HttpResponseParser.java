package com.oskm.support.remote.httpclient.parser;

import java.io.InputStream;

public interface HttpResponseParser<T> {
    T parse(InputStream responseBody) throws HttpResponseParseException;
}
