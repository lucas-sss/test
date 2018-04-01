package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/24 0024
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    public void setKeyValue(String key, String value){

        if (key == null || "".equals(key)){
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }


    public String getValue(String key){
        if (key == null || "".equals(key)){
            return null;
        }
        String value = (String) redisTemplate.opsForValue().get(key);
        return  value;
    }




}
