package com.oskm.support.remote.httpclient4.async;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Future;

public class AsyncHttpClient {

    private static final Logger LOG = Logger.getLogger(AsyncHttpClient.class);

    public void execute() throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();

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
}
