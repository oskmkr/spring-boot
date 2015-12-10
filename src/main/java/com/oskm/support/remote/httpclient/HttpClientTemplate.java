package com.oskm.support.remote.httpclient;

import com.oskm.support.remote.httpclient.parser.HttpResponseParser;

import java.io.IOException;
import java.util.Map;

public interface HttpClientTemplate<T> {

    /**
     * @param requestParameters
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     * @throws HttpClientException
     * @deprecated �� �޼���� CommonsHttpClientTemplate���� ���Ǵ� �������̽���<br>
     * {@link GetHttpClientTemplate}�� {@link PostHttpClientTemplate}�� ����Ϸ���<br> ���Ӱ� �߰��� {@link com.oskm.support.remote.httpclient.HttpClientTemplate#execute(Map)}
     * �� ����Ѵ�
     */
    @Deprecated
    public T execute(HttpClientParameters parameters) throws HttpClientException, IOException;

    /**
     * Parameter �� �����Ͽ� API ����ϴ� method
     *
     * @param parameters
     * @return
     * @throws HttpClientException
     */
    public T execute(Map<String, String> parameters) throws HttpClientException;

    /**
     * ���� ������ Parameter�� ���� ��� ����ϴ� method
     *
     * @return
     * @throws HttpClientException
     */
    public T execute() throws HttpClientException;

    /**
     * HttpClientException �߻� �� throw���� �ʰ� ����Ʈ��(����Ÿ���� String�� ���� ����, �ٸ� Ÿ���� ���� null)���� �����ϴ� �޼ҵ�
     * (client �ڵ忡�� Ư���� ó������ ���� ���� �� ����Ѵ�)
     *
     * @param requestParameters
     * @return
     * @throws IOException
     */
    public T executeQuietly(HttpClientParameters parameters) throws IOException;

    /**
     * execute �޼ҵ� ���ο��� response InputStream�� Ư�� class�� parsing�ϴµ�, parsing�� ����� parser�� inject���ִ� �޼ҵ�
     * (inject������ ���� ��� execute�޼ҵ尡 null�� �����Ѵ�.)
     *
     * @param httpResponseParser
     */
    public void setHttpResponseParser(HttpResponseParser<T> httpResponseParser);
}
