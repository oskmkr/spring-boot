/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.zookeeper;

import com.oskm.zookeeper.curator.ZookeeperTemplate;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sungkyu.eo on 2015-01-08.
 *
 * @reference http://idkbj.tistory.com/93
 */
public class ZookeeperMonitor implements Watcher, Runnable, AsyncCallback.ChildrenCallback {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperMonitor.class);

    private ZooKeeper zk;
    private boolean dead;
    private String znode;

    public ZookeeperMonitor() throws Exception {

        zk = new ZooKeeper("127.0.0.1:2181", 300000, this);
        this.znode = "/oskm";
        zk.create(znode, "myip".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        zk.getChildren(znode, true, this, null);
    }

    public static void main(String[] args) throws Exception {
        ZookeeperMonitor monitor = new ZookeeperMonitor();
        monitor.run();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();

        String eventType = watchedEvent.getType().name();

        String eventState = watchedEvent.getState().name();

        LOG.info("[process event] path : " + path + ", eventType : " + eventType + ", eventState : " + eventState);

        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            zk.getChildren(znode, true, this, null);
        }

    }

    @Override
    public void run() {

        try {
            synchronized (this) {
                while (!dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            LOG.error("[Exit]...", e);
        }
    }

    @Override
    public void processResult(int rc, String path, Object context, List<String> children) {
        for (String child : children) {

            String data = null;
            try {
                data = client.readValue("/" + child);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("## processResult: rc=" + rc + ", path=" + path + ", child= " + child + ", data = " + data);
        }
    }

    private ZookeeperTemplate client = new ZookeeperTemplate();
}
