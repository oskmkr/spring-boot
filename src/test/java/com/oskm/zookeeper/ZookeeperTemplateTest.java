/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.zookeeper;


import com.oskm.zookeeper.curator.ZookeeperTemplate;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;

public class ZookeeperTemplateTest {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperTemplateTest.class);

    private ZookeeperTemplate zookeeperClient = new ZookeeperTemplate();

    @Test
    public void curatorConnection() throws Exception {
        String conn = "127.0.0.1:2181";

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(conn).retryPolicy(retryPolicy)/*.aclProvider(new UserACLProvider())/*.authorization("zo_user", "testdrive:*****".getBytes())*/.sessionTimeoutMs(10 * 1000).build();

        client.start();

        client.getZookeeperClient().blockUntilConnectedOrTimedOut();

        client.create().forPath("/new-node3");

        client.close();
    }

    private String conn = "127.0.0.1:2181";
    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    private int connTimeoutMs = 10 * 1000;
    private int sessionTimeoutMs = 10 * 1000;

    @Test
    public void create() throws Exception {
        String path = "/node-test";
        byte[] payload = "node-test-payload".getBytes();
        zookeeperClient.create(path, payload, CreateMode.EPHEMERAL);
        zookeeperClient.create("/b/sss", payload, CreateMode.EPHEMERAL);

        List<String> result = zookeeperClient.read("/");

        LOG.debug("result" + result);

        for (String each : result) {
            LOG.debug(each);
        }
    }

    @Test
    public void read() throws Exception {

        String result = zookeeperClient.readValue("/new-node");
        LOG.debug("[read result] " + result);

    }

    @Test
    public void update() throws Exception {

        String updateValue = "updated-value";
        String originValue = zookeeperClient.readValue("/new-node");

        zookeeperClient.update("/new-node", "updated-value".getBytes());

        String actual = zookeeperClient.readValue("/new-node");

        assertThat(actual, is(updateValue));

        // rollback temp
        zookeeperClient.update("/new-node", originValue.getBytes());

    }

    @Test
    public void delete() throws Exception {

        String nodeName = "/delete-test-node";

        zookeeperClient.create(nodeName);

        String actual = zookeeperClient.readValue(nodeName);

        assertThat(actual, not(isEmptyString()));

        zookeeperClient.delete(nodeName);

        String actual2 = zookeeperClient.readValue(nodeName);

        assertThat(actual2, isEmptyString());

    }
}

