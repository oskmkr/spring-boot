/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @see https://git-wip-us.apache.org/repos/asf?p=curator.git;a=blob;f=curator-examples/src/main/java/framework/CrudExamples.java;h=c4befea31231cf841ba9a0dfeae699181cf9901c;hb=HEAD
 * Created by sungkyu.eo on 2014-12-31.
 */
public class ZookeeperTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperTemplate.class);

    /**
     * @param path
     * @param payload
     * @param createMode
     * @throws Exception
     * @see https://zookeeper.apache.org/doc/r3.1.2/api/org/apache/zookeeper/CreateMode.html
     */
    public void create(String path, byte[] payload, CreateMode createMode) throws Exception {
        getClient().create().withMode(createMode).forPath(path, payload);
    }

    public void create(String path, byte[] payload) throws Exception {
        getClient().create().forPath(path, payload);

    }

    public void create(String path) throws Exception {
        getClient().create().forPath(path);
    }

    public String readValue(String path) throws UnsupportedEncodingException {
        byte[] val = new byte[0];

        try {
            val = getClient().getData().forPath(path);
        } catch (Exception e) {
            LOG.debug("read", e);
        }

        return new String(val, "utf-8");
    }

    public List<String> read(String path) throws Exception {
        return getClient().getChildren().forPath(path);
    }

    public List<String> read(String path, Watcher watcher) throws Exception {
        return getClient().getChildren().usingWatcher(watcher).forPath(path);
    }

    public void update(String path, byte[] payload) throws Exception {
        getClient().setData().forPath(path, payload);
    }

    public void updateAsync(String path, byte[] payload) throws Exception {
        getClient().setData().forPath(path, payload);

        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                LOG.debug("event received...");
            }
        };

        getClient().getCuratorListenable().addListener(listener);

        getClient().setData().inBackground().forPath(path, payload);
    }

    public void updateWithCallback(String path, byte[] payload, BackgroundCallback callback) throws Exception {

        getClient().setData().inBackground(callback).forPath(path, payload);
    }

    public void delete(String path) throws Exception {

        getClient().delete().forPath(path);
    }

    public void deleteWithGuarantee(String path) throws Exception {
        getClient().delete().guaranteed().forPath(path);
    }

    private CuratorFramework createClient(String conn, RetryPolicy retryPolicy, int connTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder().connectString(conn).retryPolicy(retryPolicy).connectionTimeoutMs(connTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
    }

    private String conn = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    private int connectionTimeoutMs = 10 * 1000;
    private int sessionTimeoutMs = 10 * 1000;

    private CuratorFramework client;

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public CuratorFramework getClient() throws InterruptedException {
        if (client == null) {
            client = createClient(conn, retryPolicy, connectionTimeoutMs, sessionTimeoutMs);
            client.start();
            client.getZookeeperClient().blockUntilConnectedOrTimedOut();

        }

        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }
}





