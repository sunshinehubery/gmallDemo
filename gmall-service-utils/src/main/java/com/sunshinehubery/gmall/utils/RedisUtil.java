package com.sunshinehubery.gmall.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: 将redis连接池初始化到spring
 * @author: sunshinehubery
 * @date: 2019/9/14 20:36
 * @Version: 1.0
 **/
public class RedisUtil {
    private JedisPool jedisPool;
    public void initPool(String host,int port ,int database){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(30);
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(10*1000);
        poolConfig.setTestOnBorrow(true);
        jedisPool=new JedisPool(poolConfig,host,port,20*1000);
    }
    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
