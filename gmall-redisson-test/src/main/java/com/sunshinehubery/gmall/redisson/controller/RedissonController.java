package com.sunshinehubery.gmall.redisson.controller;

import com.sunshinehubery.gmall.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/15 19:14
 * @Version: 1.0
 **/
@Controller
public class RedissonController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedissonClient redissonClient;

    @RequestMapping("RedissonTest")
    @ResponseBody
    public String test(){
        Jedis jedis = redisUtil.getJedis();
        //声明锁
        RLock lock = redissonClient.getLock("lock");
        //上锁
        lock.lock();
        try {
            String v = jedis.get("k");
            if(StringUtils.isBlank(v)){
                v = "1";
            }
            System.out.println("->" + v);
            jedis.set("k", (Integer.parseInt(v) + 1) + "");
        }finally {
            jedis.close();
            //关闭锁
            lock.unlock();
        }
        return "success";
    }
}
