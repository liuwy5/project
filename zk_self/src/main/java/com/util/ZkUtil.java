package com.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZkUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkUtil.class);

    @Value("${spring.zookeeper.connect-string}")
    private String zkUrl;

    @Value("${spring.zookeeper.session.timeout}")
    private int sessionTimeout;

    private ZooKeeper zooKeeper;

    private Watcher watcher = new Watcher(){
        public void process(WatchedEvent event) {
            LOGGER.info("zookeeper watcher process: {}", event);
        }
    };

    public ZooKeeper getZooKeeper() {
        if (zooKeeper == null) {
            try {
                zooKeeper = new ZooKeeper(zkUrl, sessionTimeout, watcher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return zooKeeper;
    }

    public String get(String path) {
        String value = null;

        try {
            final byte[] bytes = getZooKeeper().getData(path, watcher, null);
            value = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
