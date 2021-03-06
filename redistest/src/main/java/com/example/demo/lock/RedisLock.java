package com.example.demo.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/19 0019
 */
@Component
public class RedisLock {

    private Random random = new Random();
    private static final Long TIMEOUT = 1000L;
    private static final Long LOCK_TIME = 1000L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Long lock(String key, Long timeout) {
        timeout = timeout == null ? TIMEOUT : timeout;
        System.out.println("开始执行加锁：" + Thread.currentThread().getName());

        Long lockTimeout = System.currentTimeMillis() + LOCK_TIME;
        Long acquireTimeout = System.currentTimeMillis() + timeout;

        while (System.currentTimeMillis() < acquireTimeout) {
            //成功获取锁
            if (redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(lockTimeout))) {
                System.out.println("线程：" + Thread.currentThread().getName() + "，加锁成功");
                /**
                 * 设置超时时间，释放内存。此操作和前面获取锁操作不是原子的
                 * 在这之前线程可能崩溃，可以考虑用setex命令或者用lua脚本
                 */
                redisTemplate.expire(key, TIMEOUT, TimeUnit.MILLISECONDS);
                return lockTimeout;
            }

            //其他线程锁失效情况下获取锁
            Object o = redisTemplate.opsForValue().get(key);
            Long old_lock_time = o == null ? null : Long.valueOf((String) o);

            if (old_lock_time != null && old_lock_time < System.currentTimeMillis()) {
                Object replaceStr = redisTemplate.opsForValue().getAndSet(key, lockTimeout + "");
                Long replace_lock_time = replaceStr == null ? null : Long.valueOf((String) replaceStr);
                if (replace_lock_time != null && old_lock_time.equals(replace_lock_time)) {
                    System.out.println("-----------------线程：" + Thread.currentThread().getName() + "，加锁成功");
                    return lockTimeout;
                }
            }

            try {
                int sleep = random.nextInt(3) * 50 + 50;
                System.out.println(Thread.currentThread().getName() + "等待加锁，随机睡眠" + sleep + "毫秒");
                //睡眠100毫秒
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("线程：" + Thread.currentThread().getName() + "等待加锁超时");
        return null;
    }

    public boolean unlok(String key, Long time) {
        Object o = redisTemplate.opsForValue().get(key);
        Long lockTime = o == null ? null : Long.valueOf((String) o);
        //仍然持有锁的情况下
        if (time != null && time.equals(lockTime)) {
            redisTemplate.delete(key);
            System.out.println("--------------------线程：" + Thread.currentThread().getName() + "，释放锁");
            return true;
        }
        return false;
    }
}
