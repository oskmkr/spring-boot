package com.oskm.support.remote.httpclient.parser;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ToJDomDocumentResponseParser implements HttpResponseParser<Document> {
    private static final String DEFAULT_CHARSET = "utf-8";
    private String charset = DEFAULT_CHARSET;

    public Document parse(InputStream responseBody) throws HttpResponseParseException {
        Document document = null;
        try {
            document = new SAXBuilder().build(new InputStreamReader(responseBody, charset));
        } catch (Exception e) {
            throw new HttpResponseParseException(e.getMessage(), e);
        }
        return document;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
