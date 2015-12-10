package com.oskm.support.remote.httpclient4.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by oskmkr on 2015-06-16.
 */
public class ToJsonNodeResponseParser implements HttpResponseParser<JsonNode> {

    @Override
    public JsonNode parse(InputStream responseBody) throws HttpResponseParseException {

        try {
            return jSONObjectFromObject(responseBody);
        } catch (IOException e) {
            throw new HttpResponseParseException(e.getMessage(), e);
        }
    }

    protected JsonNode jSONObjectFromObject(InputStream responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody);
    }

}
