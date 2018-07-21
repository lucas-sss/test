package com.example.demo.core.future;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/8
 */
public class KeyFuture implements DataCallback {

    private String privateKey;

    private volatile boolean isReady = false;

    private volatile int status;

    private static final int NEW = 0;
    private static final int RUNNING = 1;
    private static final int FINISH = 2;
    private static final int ABNORMAL = 3;


    /**
     * 获取结果，阻塞方法，如果timeout不为null
     * 在等待时间结束后没有结果将抛出异常
     *
     * @param timeout 等待超时时间（毫秒）
     * @return
     */
    public synchronized String getKey(Long timeout){

        if (status >= ABNORMAL){
            //TODO 不正常状态，channel不可用 抛出异常

        }

        while (!isReady){
            try {
                //等待两秒钟
                if (timeout != null && timeout > 0){
                    wait(timeout);
                }
                if (this.privateKey == null){
                    //TODO 抛出异常
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.privateKey;
    }

    @Override
    public synchronized void dataPadding(Object data) {
        if (isReady){
            return;
        }
        this.privateKey = (String) data;
        isReady = true;
        notify();
    }
}
