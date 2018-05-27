package com.example.demo;

import com.example.demo.domain.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/25 0025
 */
public class SerializerTest {

    public static void main(String[] args) throws Exception {

        Teacher teacher = new Teacher();

        teacher.setName("teacher");
        teacher.setAge(12);

        teacher.setX("X");
        teacher.setY("Y");



        ObjectMapper om = new ObjectMapper();

        String s = om.writeValueAsString(teacher);
        System.out.println(s);


//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

    }

}
