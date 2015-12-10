package com.oskm.support.remote.httpclient.parser;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ToByteArrayResponseParser implements HttpResponseParser<byte[]> {
    public byte[] parse(InputStream responseBody) throws HttpResponseParseException {
        try {
            return iOUtillsToByteArray(responseBody);
        } catch (IOException e) {
            throw new HttpResponseParseException(e.getMessage(), e);
        }
    }

    protected byte[] iOUtillsToByteArray(InputStream responseBody) throws IOException {
        return IOUtils.toByteArray(responseBody);
    }
}
