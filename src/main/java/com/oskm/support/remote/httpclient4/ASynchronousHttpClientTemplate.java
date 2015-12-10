/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.support.remote.httpclient4;

import com.oskm.support.remote.httpclient.HttpClientException;
import com.oskm.support.remote.httpclient.HttpClientExecutionFailException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParseException;
import com.oskm.support.remote.httpclient.parser.HttpResponseParser;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Asynchronous I/O
 * <p/>
 * Created by sungkyu.eo on 2015-04-02.
 */
public class ASynchronousHttpClientTemplate<T> implements HttpClientTemplate<T> {
    private static final Logger LOG = LoggerFactory.getLogger(ASynchronousHttpClientTemplate.class);
    private int tryCount = 3;

    public ASynchronousHttpClientTemplate() {
    }

    public ASynchronousHttpClientTemplate(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    @Override
    public T execute(Map<String, String> parameters) throws HttpClientException {
        return null;
    }

    @Override
    public T execute() throws HttpClientException {
        return null;
    }

    @Override
    public T executeQuietly(HttpClientParams parameters) throws IOException {

        try {
            return execute(parameters);
        } catch (HttpClientException e) {
            LOG.warn("# execute failed", e);
            return null;
        }

    }
/*

    public T execute(HttpClientParams param) throws HttpClientException, IOException {

        try {
            httpClient.start();

            HttpGet httpGet = new HttpGet("http://www.samsung.net/112123.nhn");

            Future<HttpResponse> future = httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {

                @Override
                public void failed(Exception arg0) {
                    LOG.debug("## failed");

                }

                @Override
                public void completed(HttpResponse arg0) {
                    LOG.debug("## completed");

                }

                @Override
                public void cancelled() {
                    LOG.debug("## canceled");

                }
            });

            HttpResponse response = future.get();

            LOG.debug("Response : " + response.getStatusLine());
            LOG.debug("Shutting down");

        } catch (Exception e) {
            LOG.error(e, e);
        } finally {
            httpClient.close();
        }

        LOG.debug("async http request done...");
    }
*/

    public T execute(HttpClientParams parameters) throws HttpClientException, IOException {
        CloseableHttpAsyncClient httpClient = createHttpAsyncClient();
        HttpUriRequest httpUriRequest = createHttpMethod(methodType, parameters);

        return invoke(httpClient, httpUriRequest);
    }

    protected CloseableHttpAsyncClient createHttpAsyncClient() {

        // TODO : PoolingHttpClientConnectionManager
        //HttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();

        HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig);/*.setSSLSocketFactory(findSslConnectionSocketFactory())*/

        if (null != proxyManager) {
            httpClientBuilder.setRoutePlanner(proxyManager.findRoutePlanner());
        }

        CloseableHttpAsyncClient httpClient = httpClientBuilder.build();

		/*
         * httpClient.getHttpConnectionManager().getParams().setConnectionTimeout
		 * (connectionTimeout);
		 * httpClient.getHttpConnectionManager().getParams()
		 * .setSoTimeout(readTimeout);
		 */
        return httpClient;
    }

    private HttpUriRequest createHttpMethod(String methodType, HttpClientParams parameters) throws HttpClientException {

        HttpClientParams httpParameters = parameters;

        if (httpParameters == null) {
            httpParameters = new HttpClientParams();
        }

        String requestUrl = getRequestUrl(httpParameters.getRequestParameters());

        HttpRequestBase httpMethod = null;


        if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpGet(requestUrl);
        } else if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpPut(requestUrl);
        } else if (HttpDelete.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpDelete(requestUrl);
        } else if (HttpHead.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpHead(requestUrl);
        } else if (HttpTrace.METHOD_NAME.equalsIgnoreCase(methodType)) {
            httpMethod = new HttpTrace(requestUrl);
        } else { // post
            httpMethod = new HttpPost(requestUrl);
        }

        httpMethod.setConfig(RequestConfig.custom().setConnectTimeout(connectionTimeout).setSocketTimeout(readTimeout).build());

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

    protected T invoke(CloseableHttpAsyncClient httpClient, HttpUriRequest httpUriRequest) throws HttpClientException, IOException {
        T resultObject = null;
        try {
            Future<HttpResponse> future = null;

            httpClient.start();

            for (int i = 0; i < tryCount; i++) {
                future = httpClient.execute(httpUriRequest, new FutureCallback<HttpResponse>() {
                    @Override
                    public void completed(HttpResponse httpResponse) {

                    }

                    @Override
                    public void failed(Exception e) {

                    }

                    @Override
                    public void cancelled() {

                    }
                });

                HttpResponse response = future.get();

                if (isSuccessfulStatus(response)) {

                    HttpEntity httpEntity = response.getEntity();

                    //LOG.debug("IO", IOUtils.toString(httpEntity.getContent()));
                    resultObject = parseResponseBody(httpEntity.getContent());
                    break;
                }
                validateResponse(response);
            }
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

    private boolean isSuccessfulStatus(HttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    protected void validateResponse(HttpResponse response) throws HttpClientExecutionFailException {
        if (!isSuccessfulStatus(response)) {
            throw new HttpClientExecutionFailException(response.getStatusLine().getStatusCode(), this.url);
        }
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

    public SSLConnectionSocketFactory findSslConnectionSocketFactory() {
        KeyStore trustStore = null;
        SSLContext sslContext = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            TrustStrategy allTrust = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, allTrust).build();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    protected T parseResponseBody(InputStream responseBody) throws Exception {
        if (httpResponseParser == null) {
            return null;
        }
        return (T) httpResponseParser.parse(responseBody);
    }

    private String url; // with template
    private String methodType; // get or post
    private String contentType = "text/html"; // contentType
    private String contentCharset = "utf-8"; // for post method
    private String urlEncoding; // for get method only

    private int connectionTimeout;
    private int readTimeout;

    @Autowired
    @Qualifier("proxyManager")
    private ProxyManager proxyManager;

    private HttpResponseParser httpResponseParser;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public void setUrlEncoding(String urlEncoding) {
        this.urlEncoding = urlEncoding;
    }

    public void setProxyManager(ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    public void setHttpResponseParser(HttpResponseParser<T> httpResponseParser) {
        this.httpResponseParser = httpResponseParser;

    }

}
