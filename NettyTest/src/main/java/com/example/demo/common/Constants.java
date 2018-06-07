package com.example.demo.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class Constants {

    /**
     * 消息体开始标志
     */
    public static final byte MSG_START_FLAG = (byte)0xE0;
    /**
     * 消息体结束标志
     */
    public static final byte MSG_END_FLAG = (byte)0xEE;


    public static AtomicLong clientLable = new AtomicLong();
}
