package com.yizhuoyang.classroomfeatures.util;

import com.yizhuoyang.classroomfeatures.component.JedisPoolFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Component
public class JedisUtil {

    private final JedisPoolFactory factory;
    //自动注入
    @Autowired
    public JedisUtil(JedisPoolFactory factory) {
        this.factory = factory;
    }
    //获得连接
    private Jedis getResource() {
        return factory.getJedisPool().getResource();
    }

    public byte[] set(byte[] key, byte[] value) {
        try (Jedis jedis = getResource()) {
            jedis.set(key, value);
            return value;
        }
    }
//操作redis过期时间
    public void expire(byte[] key, int i) {
        try (Jedis jedis = getResource()) {
            jedis.expire(key, i);
        }
    }

    public byte[] get(byte[] key) {
        try (Jedis jedis = getResource()) {
            return jedis.get(key);
        }
    }

    public void del(byte[] key) {
        try (Jedis jedis = getResource()) {
            jedis.del(key);
        }
    }
    //加前缀为了区分，用*分隔开
    public Set<byte[]> keys(String shiroSessionPrefix) {
        try (Jedis jedis = getResource()) {
            return jedis.keys((shiroSessionPrefix + "*").getBytes());
        }
    }


}
