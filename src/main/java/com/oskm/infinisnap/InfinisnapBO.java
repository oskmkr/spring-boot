package com.oskm.infinisnap;

import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class InfinisnapBO {

    private static Logger LOG = Logger.getLogger(InfinisnapBO.class);

    public void connect() {

        EmbeddedCacheManager cacheManager = new DefaultCacheManager();

        Cache<Object, Object> cache = cacheManager.getCache();

        cache.put("key", "value");
        cache.put("key1", "value");
        cache.put("key2", "value");

        System.out.println(cache.size());

        LOG.debug(cache);

    }

    public void connectRemote() {

        RemoteCacheManager cacheManager = new RemoteCacheManager("localhost");

        RemoteCache cache = cacheManager.getCache();

        LOG.debug("key value" + cache.get("key"));

        cache.put("key", "���");
        cache.put("key1", "value");
        cache.put("key2", "value");

        System.out.println(cache.size());

        LOG.debug(cache);

    }
}
