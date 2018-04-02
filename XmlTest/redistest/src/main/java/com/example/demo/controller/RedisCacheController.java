package com.example.demo.controller;

import com.example.demo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/25 0025
 */
@RestController
public class RedisCacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheController.class);

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping (value= "/rediscache/get")
    @Cacheable(value = "getRedisCache", key = "'cache_' + #key")
    public String getCache(String key) {

        System.out.println("没走缓存了，key=" + key);

        String value = redisUtil.getValue(key);
        if (value == null){
            value = "null";
        }
        return value;
    }


    @GetMapping (value= "/rediscache/delete")
    @CacheEvict(value = "deleteRedisCache", key = "'cache_' + #key")
    public void deleteCache(String key){
        LOGGER.debug("清除缓存数据key:{}", key);
        System.out.println("清除缓存数据：" + key);
    }
}
