package com.example.demo.lock;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/19 0019
 */
public class LockTestTask implements Runnable {

    private static int num = 50;
    private RedisLock lock;
    private Object obj = new Object();

    public LockTestTask(RedisLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Long lockTime;
        while (true){

            if ((lockTime = lock.lock("lock_t", 500L)) != null) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ", 卖出一票，余票" + (--num));
                }
                lock.unlok("lock_t", lockTime);
            }
        }
    }
}
