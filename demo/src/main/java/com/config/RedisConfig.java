package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {

//    @Value("${spring.redis.host}")
    private String host;

//    @Value("${spring.redis.port}")
    private Integer port;

//    @Value("${spring.redis.auth}")
    private String auth;

//    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxTotal;

//    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;

//    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWaitMillis;

//    @Autowired
    JedisPoolConfig config;

//    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(config, host, port, 2000, auth);
    }

//    @Bean
    public JedisPoolConfig jedisPoolConfig () {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }
}







