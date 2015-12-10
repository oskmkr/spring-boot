/* 
 * ToJSONObjectResponseParser.java 2010. 6. 21.
 *
 * Copyright 2010 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.oskm.support.remote.httpclient.parser;

import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * JSONObject Parser
 *
 * @author ?�승�?
 * @since 2010. 6. 21.
 */
public class ToJSONObjectResponseParser implements HttpResponseParser<JSONObject> {
    /**
     * @param responseBody
     * @return
     * @throws HttpResponseParseException
     * @see com.naver.cafe.support.remote.httpclient.parser.httpclient.HttpResponseParser#parse(java.io.InputStream)
     */
    @Override
    public JSONObject parse(InputStream responseBody) throws HttpResponseParseException {
        try {
            return jSONObjectFromObject(responseBody);
        } catch (IOException e) {
            throw new HttpResponseParseException(e.getMessage(), e);
        }
    }

    protected JSONObject jSONObjectFromObject(InputStream responseBody) throws IOException {
        return JSONObject.fromObject(IOUtils.toString(responseBody, "utf-8"));
    }

}
