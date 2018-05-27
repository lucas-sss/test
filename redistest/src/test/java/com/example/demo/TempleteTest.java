package com.example.demo;

import com.example.demo.domain.Teacher;
import com.example.demo.lock.LockTestTask;
import com.example.demo.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TempleteTest {


    @Resource(name = "myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisLock lock;


    @Resource(name = "lock")
    private RedisScript<Boolean> redisScript;

    int num = 100;

    @Test
    public void hashGetTest() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Map test_hash = redisTemplate.opsForHash().entries("test_hash");
            List<Teacher> list = new ArrayList<>();
            for (Object o : test_hash.keySet()) {
                Teacher t =(Teacher) test_hash.get(o);
//                System.out.println(t.getAge());
                list.add(t);
            }
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));


    }

    @Test
    public void hashSetTest() {

        for (int i = 0; i < 20; i++) {
            Teacher t = new Teacher();
            t.setName("name" + i);
            t.setAge(i);
            t.setX("x-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setY("y-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setZ("z-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setExtend("2018-05-25 23:59:25,797 INFO (AbstractHandlerMethodMapping.java:543)- Mapped \"{[/error],produces=[text/html]}\" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)");
            redisTemplate.opsForHash().put("test_hash", t.getName(), t);
        }

    }



    @Test
    public void rangeTest() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            List test_list_range = redisTemplate.opsForList().range("test_list_range", 0, 20);
            List<Teacher> list = new ArrayList<>(25);
            for (Object o : test_list_range) {
                Teacher t = (Teacher)o;
                list.add(t);
            }
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));


    }

    @Test
    public void popTest() {
        int i = 0;
        while (true){
            Object o = redisTemplate.opsForList().rightPop("test_list_range");
            if (o ==null){
                break;
            }
            i++;
        }
        System.out.println("弹出元素个数：" + i);
    }

    @Test
    public void pushTest() {
        for (int i = 0; i < 20; i++) {
            Teacher t = new Teacher();
            t.setName("name" + i);
            t.setAge(i);
            t.setX("x-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setY("y-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setZ("z-Multiple Spring Data modules found, entering strict repository configuration mode!" + i);
            t.setExtend("2018");
            redisTemplate.opsForList().leftPush("test_list_range", t);
        }
    }



    @Test
    public void basicTest() {

        redisTemplate.opsForValue().set("age", "12");
        Object name = redisTemplate.opsForValue().get("age");
        System.out.println(name);

    }

    @Test
    public void lockTest() throws Exception {

        for (int i = 0; i < 30; i++) {

            int j = i;
            new Thread(() -> {
                while (true){
                    Long lockTime;
                    if ((lockTime = lock.lock("lock_t", 500L)) != null){
                        if (num > 0){
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++第" + j + "个线程:" + (--num));
                        }
                        lock.unlok("lock_t", lockTime);
                    }
                    try {
                        Thread.sleep(new Random().nextInt(3) * 300 + 500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        Thread.sleep(50000);
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


    @Test
    public void luaTest() throws Exception {
        long time = System.currentTimeMillis() + 1000L;
        Boolean execute = (Boolean) redisTemplate.execute(redisScript, Collections.singletonList("lock_test"), 100);

        System.out.println(execute);

    }


}
