package com.funny.study.oauth.config;

import com.funny.study.oauth.utils.jedis.JedisClientPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

/**
 * @author funnystack
 * @date 2018/4/19 23:05
 */
@Configuration
@ConfigurationProperties(prefix = "redis.standalone")
public class JedisConfig {
    private String host;

    private Integer port;

    private String password;

    @Bean
    public JedisClientPool jedisClientPool() {
        JedisClientPool jedisClientPool = new JedisClientPool();
        jedisClientPool.setJedisPool(jedisPool());

        return jedisClientPool;
    }

    @Bean
    public JedisPool jedisPool() {
        if(StringUtils.isEmpty(password)) {
            return new JedisPool(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT);
        } else {
            return new JedisPool(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, password, Protocol.DEFAULT_DATABASE, null);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
