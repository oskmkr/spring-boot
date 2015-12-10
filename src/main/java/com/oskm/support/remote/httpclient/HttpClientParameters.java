package com.oskm.support.remote.httpclient;

import com.oskm.support.BaseObject;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.multipart.FilePart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("serial")
public class HttpClientParameters extends BaseObject {
    List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
    String requestEntityContent;
    List<Header> requestHeaders = new ArrayList<Header>();
    Map<String, String> headerMap = new HashMap<String, String>();
    Map<String, File> fileParameters = new HashMap<String, File>();

    public NameValuePair[] getRequestParameters() {
        return requestParameters.toArray(new NameValuePair[0]);
    }

    /**
     * request parameter�?추�?
     * (POST, PUT 방식?�서�??�용)
     */
    public HttpClientParameters addRequestParameter(String name, String value) {
        requestParameters.add(new NameValuePair(name, value));
        return this;
    }

    /**
     * string request entity�?추�?
     * (POST, PUT 방식?�서�??�용)
     */
    public HttpClientParameters addStringRequestEntity(String requestEntityContent) {
        this.requestEntityContent = requestEntityContent;
        return this;
    }

    public String getStringRequestEntityContent() {
        return requestEntityContent;
    }

    public boolean hasRequestParameters() {
        return !requestParameters.isEmpty();
    }

    @Deprecated
    public List<Header> getRequestHeaders() {
        return requestHeaders;
    }

    @Deprecated
    public HttpClientParameters addRequestHeader(String name, String value) {
        // commons httpclient???�위?�환?�을 �??�??�해 commons ?�키�?�� Header�??��??�고 headerMap??추�??�다.
        requestHeaders.add(new Header(name, value));
        headerMap.put(name, value);
        return this;
    }

    public Map<String, File> getFileParameters() {
        return fileParameters;
    }

    public boolean hasFileParameters() {
        return !fileParameters.isEmpty();
    }

    public FilePart[] getFileParts() {

        FilePart[] fileParts = new FilePart[fileParameters.size()];

        int i = 0;
        for (String key : fileParameters.keySet()) {
            File value = fileParameters.get(key);
            try {
                fileParts[i] = new FilePart(key, value);
            } catch (FileNotFoundException e) {
                throw new HttpClientException("filename:" + value, e);
            }
            i++;
        }

        return fileParts;
    }

    public HttpClientParameters addFile(String name, File value) {
        fileParameters.put(name, value);
        return this;
    }

    public Set<String> getHeaderNames() {
        return headerMap.keySet();
    }

    public String getHeaderValue(String name) {
        return headerMap.get(name);
    }
}
