package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TempleteTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void basicTest() {


        redisTemplate.opsForValue().set("age", "12");

        Object name = redisTemplate.opsForValue().get("age");


        System.out.println(redisTemplate.opsForValue());


        System.out.println(name);

    }
}
