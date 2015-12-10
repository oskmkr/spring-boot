package com.oskm.support.remote.httpclient4;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.HttpClientExecutionFailException;
import com.oskm.support.remote.httpclient.HttpClientParameters;
import com.oskm.support.remote.httpclient.HttpClientTemplate;
import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import com.oskm.support.remote.httpclient.parser.ToStringResponseParser;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.DefaultSchemePortResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpComponentClientTemplate<Object> implements HttpClientTemplate {
    private static Log LOG = LogFactory.getLog(HttpComponentClientTemplate.class);

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String DELETE_METHOD = "POST";
    private static final String PUT_METHOD = "POST";

    private static final int DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS = 200;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 2 * 1000;
    private static final String DEFAULT_CONTENT_CHARSET = "UTF-8";
    private static final int DEFAULT_RETRY_COUNT = 3;

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

    private String proxyHost;
    private Integer proxyPort;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    private HttpResponseParser httpResponseParser;

    /**
     * @param url        in case of GET method, wrap parameter value with {}. ex)
     *                   http://api.nid.naver.com/nidapi/userinfo_api.nhn?code=
     *                   AAC_userinfo_13&id={userId}
     * @param methodType
     */
    public HttpComponentClientTemplate(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    /**
     * @param url
     * @param methodType
     * @param contentType
     */
    public HttpComponentClientTemplate(String url, String methodType, String contentType) {
        this(url, methodType);
        this.contentType = contentType;
    }

    /**
     * @param requestParameters
     * @return
     * @throws HttpClientException
     * @throws IOException
     */
    public Object execute(HttpClientParameters parameters) throws HttpClientException, IOException {
        CloseableHttpClient httpClient = createHttpClient();
        HttpUriRequest httpUriRequest = createHttpMethod(methodType, parameters);

        return invoke(httpClient, httpUriRequest);
    }

    /**
     *
     *
     * @param requestParameters
     * @return
     * @throws IOException
     */
    public Object executeQuietly(HttpClientParameters parameters) throws IOException {
        try {
            return execute(parameters);
        } catch (HttpClientException e) {
            LOG.warn(e);
            if (httpResponseParser instanceof ToStringResponseParser) {
                return (Object) "";
            }
            return null;
        }
    }

    protected CloseableHttpClient createHttpClient() {

        // TODO : PoolingHttpClientConnectionManager
        // new PoolingHttpClientConnectionManager();
        HttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();

        CloseableHttpClient httpClient = HttpClients.custom().setRoutePlanner(findRoutePlanner()).setConnectionManager(connManager).build();

		/*
         * httpClient.getHttpConnectionManager().getParams().setConnectionTimeout
		 * (connectionTimeout);
		 * httpClient.getHttpConnectionManager().getParams()
		 * .setSoTimeout(readTimeout);
		 */
        return httpClient;
    }

    private HttpRoutePlanner findRoutePlanner() {

        if (StringUtils.isBlank(this.getProxyHost())) {
            return new DefaultRoutePlanner(new DefaultSchemePortResolver());
        }

        HttpHost proxy = new HttpHost(this.getProxyHost(), this.getProxyPort());
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        return routePlanner;
    }

    private HttpUriRequest createHttpMethod(String methodType, HttpClientParameters parameters) throws HttpClientException {

        HttpClientParameters httpParameters = parameters;

        if (httpParameters == null) {
            httpParameters = new HttpClientParameters();
        }

        String requestUrl = getRequestUrl(httpParameters.getRequestParameters());

        HttpRequestBase httpMethod = null;

        if (GET_METHOD.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpGet(requestUrl);
        } else if (PUT_METHOD.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpPut(requestUrl);
        } else if (DELETE_METHOD.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpDelete(requestUrl);
        } else { // post
            httpMethod = new HttpPost(requestUrl);
            // addParameters�� �߰��Ǹ� body�� ���� ����� �Ķ���Ͱ� �����ȴ�. �ϴ��� entity �����õ�
            // clear �� �����ȴ�.
			/*
			 * postMethod.addParameters(httpParameters.getRequestParameters());
			 * httpMethod = postMethod;
			 */
        }

        if (null != httpMethod) {
            httpMethod.setConfig(RequestConfig.custom().setConnectTimeout(connectionTimeout).setSocketTimeout(readTimeout).build());
        }

		/*
		 * // set request headers for (Header header :
		 * httpParameters.getRequestHeaders()) {
		 * httpMethod.addRequestHeader(header); }
		 * 
		 * // set request entity & content charset if (httpMethod instanceof
		 * EntityEnclosingMethod) { // entity�� body�� �����Ǹ� addParameters�� ������
		 * body�� Ŭ���� �ȴ�. processEntityEnclosingMethod(httpParameters,
		 * httpMethod); }
		 */

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

    protected Object invoke(CloseableHttpClient httpClient, HttpUriRequest httpUriRequest) throws HttpClientException, IOException {
        Object resultObject = null;
        try {
            CloseableHttpResponse response = null;
            for (int i = 0; i < tryCount; i++) {
                response = doExecuteMethod(httpClient, httpUriRequest);

                if (isSuccessfulStatus(response)) {

                    HttpEntity httpEntity = response.getEntity();

                    resultObject = parseResponseBody(httpEntity.getContent());
                    break;
                }
            }
            validateResponse(response);
        } catch (HttpResponseParseException e) {
            LOG.error("URL[" + httpUriRequest.getURI().getRawPath() + "]", e);
            throw new HttpClientException(e.getMessage(), e);
        } catch (Exception e) {
            throw new HttpClientException(e.getMessage() + ", URL[" + httpUriRequest.getURI().getRawPath() + "]", e);
        } finally {
            httpClient.close();
        }
        return resultObject;
    }

    private boolean isSuccessfulStatus(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    protected String getURI(HttpMethod httpMethod) {
        try {
            return httpMethod.getURI().toString();
        } catch (URIException e) {
            return this.url;
        }
    }

    protected CloseableHttpResponse doExecuteMethod(CloseableHttpClient httpClient, HttpUriRequest httpRequestBase) throws HttpException, IOException {
        return httpClient.execute(httpRequestBase);
    }

    protected void validateResponse(CloseableHttpResponse response) throws HttpClientExecutionFailException {
        if (!isSuccessfulStatus(response)) {
            throw new HttpClientExecutionFailException(response.getStatusLine().getStatusCode(), this.url);
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
            if (LOG.isDebugEnabled()) {
                LOG.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
            }
            return requestUrl;
        }
        for (NameValuePair requestParam : requestParameters) {
            requestUrl = StringUtils.replace(requestUrl, "{" + requestParam.getName() + "}", urlEncode(StringUtils.defaultString(requestParam.getValue())));
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("* CommonsHttpClientTemplate Request URL : " + requestUrl);
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
