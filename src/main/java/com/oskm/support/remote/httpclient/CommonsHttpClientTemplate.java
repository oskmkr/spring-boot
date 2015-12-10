package com.oskm.support.remote.httpclient;

import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import com.oskm.support.remote.httpclient.parser.ToStringResponseParser;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class CommonsHttpClientTemplate<Object> implements HttpClientTemplate {
    private static Log log = LogFactory.getLog(CommonsHttpClientTemplate.class);

    static final String HMAC_GET_METHOD = "hmac";
    static final String GET_METHOD = "get";
    static final String POST_METHOD = "post";

    static final int DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS = 200;
    static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 2 * 1000;
    static final String DEFAULT_CONTENT_CHARSET = "UTF-8";
    static final int DEFAULT_RETRY_COUNT = 3;
    public static final String DEFAULT_CONTENT_TYPE = "text/html";
    public static final String JSON_CONTENT_TYPE = "application/json";

    private String url; // with template
    private String methodType; // get or post
    private String contentType = DEFAULT_CONTENT_TYPE; // contentType
    private String contentCharset = DEFAULT_CONTENT_CHARSET; // for post method
    // only
    private String urlEncoding; // for get method only
    private int tryCount = DEFAULT_RETRY_COUNT;
    private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS;
    private int readTimeout = DEFAULT_READ_TIMEOUT_MILLISECONDS;

    private HttpResponseParser httpResponseParser;

    /**
     * @param url        in case of GET method, wrap parameter value with {}. ex)
     *                   http://api.nid.naver.com/nidapi/userinfo_api.nhn?code=
     *                   AAC_userinfo_13&id={userId}
     * @param methodType
     */
    public CommonsHttpClientTemplate(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    /**
     * @param url
     * @param methodType
     * @param contentType
     */
    public CommonsHttpClientTemplate(String url, String methodType, String contentType) {
        this(url, methodType);
        this.contentType = contentType;
    }

    /**
     * @param parameters
     * @return
     * @throws HttpClientException
     */
    public Object execute(HttpClientParameters parameters) throws HttpClientException {
        HttpClient httpClient = createHttpClient();
        HttpMethod httpMethod = createHttpMethod(methodType, parameters);

        httpClient.getHostConfiguration().setProxy("168.219.61.252", 8080);

        return invoke(httpClient, httpMethod);
    }

    /**
     * HttpClientException �߻� �� throw���� �ʰ� ����Ʈ��(����Ÿ���� String�� ���� ���, �ٸ� Ÿ���� ����
     * null)���� �����ϴ� �޼ҵ� (client �ڵ忡�� Ư���� ó������ ���� ���� �� ����Ѵ�)
     *
     * @param parameters
     * @return
     */
    public Object executeQuietly(HttpClientParameters parameters) {
        try {
            return execute(parameters);
        } catch (HttpClientException e) {
            log.warn(e);
            if (httpResponseParser instanceof ToStringResponseParser) {
                return (Object) "";
            }
            return null;
        }
    }

    protected HttpClient createHttpClient() {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(readTimeout);

        return httpClient;
    }

    private HttpMethod createHttpMethod(String methodType, HttpClientParameters parameters) throws HttpClientException {

        HttpClientParameters httpParameters = parameters;

        if (httpParameters == null) {
            httpParameters = new HttpClientParameters();
        }

        String requestUrl = getRequestUrl(httpParameters.getRequestParameters());

        HttpMethod httpMethod = null;

        if (GET_METHOD.equalsIgnoreCase(methodType)) {
            httpMethod = new GetMethod(requestUrl);
        } else if ("put".equalsIgnoreCase(methodType)) {
            httpMethod = new PutMethod(requestUrl);
        } else if ("delete".equalsIgnoreCase(methodType)) {
            httpMethod = new DeleteMethod(requestUrl);
        } else { // post
            PostMethod postMethod = new PostMethod(requestUrl);
            // addParameters�� �߰��Ǹ� body�� ���� ����� �Ķ���Ͱ� �����ȴ�. �ϴ��� entity �����õ�
            // clear �� �����ȴ�.
            postMethod.addParameters(httpParameters.getRequestParameters());
            httpMethod = postMethod;
        }

        // set request headers
        for (Header header : httpParameters.getRequestHeaders()) {
            httpMethod.addRequestHeader(header);
        }

        // set request entity & content charset
        if (httpMethod instanceof EntityEnclosingMethod) {
            // entity�� body�� �����Ǹ� addParameters�� ������ body�� Ŭ���� �ȴ�.
            processEntityEnclosingMethod(httpParameters, httpMethod);
        }

        return httpMethod;
    }

    private void processEntityEnclosingMethod(HttpClientParameters parameters, HttpMethod httpMethod) {
        // set content charset
        httpMethod.getParams().setContentCharset(contentCharset);

        // set string request entity
        if (parameters.getStringRequestEntityContent() != null) {
            try {
                RequestEntity requestEntity = new StringRequestEntity(parameters.getStringRequestEntityContent(), contentType, contentCharset);
                ((EntityEnclosingMethod) httpMethod).setRequestEntity(requestEntity);
            } catch (UnsupportedEncodingException e) {
                throw new HttpClientException(e.getMessage());
            }
        }

        // set multipart request entity
        if (parameters.hasFileParameters()) {
            MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parameters.getFileParts(), httpMethod.getParams());
            ((EntityEnclosingMethod) httpMethod).setRequestEntity(multipartRequestEntity);
        }
    }

    protected Object invoke(HttpClient httpClient, HttpMethod httpMethod) throws HttpClientException {
        Object resultObject = null;
        try {

            ProtocolSocketFactory socketFactory = new EasySSLProtocolSocketFactory();
            Protocol https = new Protocol("https", socketFactory, 443);
            Protocol.registerProtocol("https", https);

            for (int i = 0; i < tryCount; i++) {
                doExecuteMethod(httpClient, httpMethod);
                if (isSuccessfulStatus(httpMethod)) {
                    resultObject = parseResponseBody(httpMethod.getResponseBodyAsStream());
                    break;
                }
            }
            validateResponse(httpMethod);
        } catch (HttpResponseParseException e) {
            log.error("URL[" + getURI(httpMethod) + "]", e);
            throw new HttpClientException(e.getMessage(), e);
        } catch (Exception e) {
            throw new HttpClientException(e.getMessage() + ", URL[" + getURI(httpMethod) + "]", e);
        } finally {
            httpMethod.releaseConnection();
        }
        return resultObject;
    }

    protected String getURI(HttpMethod httpMethod) {
        try {
            return httpMethod.getURI().toString();
        } catch (URIException e) {
            return this.url;
        }
    }

    protected void doExecuteMethod(HttpClient httpClient, HttpMethod httpMethod) throws HttpException, IOException {
        httpClient.executeMethod(httpMethod);
    }

    protected void validateResponse(HttpMethod httpMethod) throws HttpClientExecutionFailException {
        if (!isSuccessfulStatus(httpMethod)) {
            throw new HttpClientExecutionFailException(httpMethod.getStatusCode(), this.url);
        }
    }

    protected boolean isSuccessfulStatus(HttpMethod httpMethod) {
        return httpMethod.getStatusCode() == HttpStatus.SC_OK;
    }

    protected Object parseResponseBody(InputStream responseBody) throws Exception {
        if (httpResponseParser == null) {
            return null;
        }
        return (Object) httpResponseParser.parse(responseBody);
    }

    protected String getRequestUrl(NameValuePair[] requestParameters) {
        String requestUrl = this.url;
        if (requestParameters.length == 0) {
            if (log.isDebugEnabled()) {
                log.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
            }
            return requestUrl;
        }
        for (NameValuePair requestParam : requestParameters) {
            requestUrl = StringUtils.replace(requestUrl, "{" + requestParam.getName() + "}", urlEncode(StringUtils.defaultString(requestParam.getValue())));
        }
        if (log.isDebugEnabled()) {
            log.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
        }

        return requestUrl;
    }

    protected String urlEncode(String src) {
        if (this.urlEncoding == null) {
            return src;
        }

        try {
            return URLEncoder.encode(src, urlEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new HttpClientException(e.getMessage());
        }
    }

    public void setConnectionTimeout(int connectionTimeout) {
        if (connectionTimeout < 0) {
            throw new IllegalArgumentException("connectionTimeout must be a non-negative value");
        }
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        if (readTimeout < 0) {
            throw new IllegalArgumentException("readTimeout must be a non-negative value");
        }
        this.readTimeout = readTimeout;
    }

    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setTryCount(int tryCount) {
        int tmpTryCount = tryCount;

        if (tmpTryCount < 1) {
            tmpTryCount = 1;
        }
        this.tryCount = tmpTryCount;
    }

    public void setHttpResponseParser(HttpResponseParser httpResponseParser) {
        this.httpResponseParser = httpResponseParser;
    }

    public void setUrlEncoding(String urlEncoding) {
        this.urlEncoding = urlEncoding;
    }

    @Override
    public Object execute(Map parameters) throws HttpClientException {
        throw new UnsupportedOperationException(" * CommonsHttpClientTemplate.execute() is not supported any longer. use execute(Map<String, String>) method.");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.naver.cafe.support.remote.httpclient.HttpClientTemplate#execute()
     */
    @Override
    public Object execute() throws HttpClientException {
        throw new UnsupportedOperationException(" * CommonsHttpClientTemplate.execute() is not supported any longer. use execute() method.");
    }

}
