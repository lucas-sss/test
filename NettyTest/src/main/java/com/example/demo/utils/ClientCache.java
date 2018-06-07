package com.example.demo.utils;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/6
 */
public class ClientCache {


    private static ConcurrentHashMap<String, Channel> cache = new ConcurrentHashMap();




    public static void add(String lable, Channel channel){
        cache.put(lable, channel);
    }

    public static Channel search(String lable){
        if (cache.containsKey(lable)){
            return cache.get(lable);
        }
        return null;
    }


}
