package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/24 0024
 */
@Configuration
@EnableCaching
public class RedisConfig {


    @Bean("cache")
    public RedisScript<Integer> defaultRedisScript() {
        DefaultRedisScript<Integer> redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("lua/test.lua"));
        redisScript.setResultType(Integer.class);
        return redisScript;
    }

    @Bean("lock")
    public RedisScript<Boolean> lockRedisScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("lua/acquire_lock.lua"));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }


    @Bean(name = "myRedisTemplate")
    public RedisTemplate getRedisTemplate(RedisConnectionFactory connectionFactory) {
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(connectionFactory);
//        template.setValueSerializer(new StringRedisSerializer());
        // 设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器
        //使用Jackson 2，将对象序列化为JSON
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //json转对象类，不设置默认的会将json转成hashmap
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

}
