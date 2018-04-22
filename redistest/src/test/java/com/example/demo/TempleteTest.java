package com.example.demo;

import com.example.demo.lock.LockTestTask;
import com.example.demo.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

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

    @Autowired
    private RedisLock lock;


    int num = 5;

    @Test
    public void basicTest() {

        redisTemplate.opsForValue().set("age", "12");
        Object name = redisTemplate.opsForValue().get("age");
        System.out.println(redisTemplate.opsForValue());
        System.out.println(name);

    }

    @Test
    public void lockTest() throws Exception {

        CountDownLatch latch = new CountDownLatch(1);
//        int num = 5;

        for (int i = 0; i < 30; i++) {

//            new Thread(new LockTestTask(latch, num, lock)).start();
            int j = i;
            new Thread(() -> {
//                try {
//                    latch.wait();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                while (true){
                    if (lock.lock("test", 1000L) != null){
                        if (num > 0){
                            System.out.println("第" + j + "个线程:" + (--num));
                        }
                    }
                    try {
                        Thread.sleep(new Random().nextInt(3) * 300 + 500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        Thread.currentThread().wait();
    }

    @Test
    public void lockTest2() throws Exception {

        Thread thread1 = new Thread(new LockTestTask(lock));
        thread1.setName("窗口1");
        Thread thread2 = new Thread(new LockTestTask(lock));
        thread2.setName("窗口2");
        Thread thread3 = new Thread(new LockTestTask(lock));
        thread3.setName("窗口3");
        Thread thread4 = new Thread(new LockTestTask(lock));
        thread4.setName("窗口4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(50000);
    }
}
