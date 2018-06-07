package com.example.demo.core;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class ChannelContext {

    private static ConcurrentHashMap<String, Channel> cctx = new ConcurrentHashMap<>();


    public static Channel search(String lable){

        if (cctx.containsKey(lable)){
            return cctx.get(lable);
        }

        return null;
    }

    public static void cache(String lable, Channel channel){
        cctx.put(lable, channel);
    }

    public static boolean remove(String lable){
        if (cctx.containsKey(lable)){
            cctx.remove(lable);
            return true;
        }
        return false;
    }

    public static int size(){
        return cctx.size();
    }

    public static Set<Map.Entry<String, Channel>> entries(){

        Set<Map.Entry<String, Channel>> entries = cctx.entrySet();
        return entries;
    }

    public static ConcurrentHashMap<String, Channel> getCctx() {
        return cctx;
    }
}
