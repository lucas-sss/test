package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/1 0001
 */
@RestController
public class RedisLuaController {

    @Autowired
    private RedisScript<Integer> redisScript;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @RequestMapping("/testlua/{key}")
    public Object testLua(@PathVariable("key") String key){
        System.out.println("key:" + key);

//        redisTemplate.opsForValue().set(key, "aaaa");
//        Object execute = redisTemplate.execute(redisScript, Collections.singletonList(key), "aaaa", "123");
        Object execute = redisTemplate.execute(redisScript, Collections.singletonList(key), "3", "10");
        System.out.println(execute);
        return execute;
    }

}
