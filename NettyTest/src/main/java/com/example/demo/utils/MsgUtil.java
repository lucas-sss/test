package com.example.demo.utils;

import java.util.UUID;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/1 0001
 */
public class MsgUtil {

    private static final UUID uuid = UUID.randomUUID();

    public static String getUuid(){
        String s = uuid.toString();
        return s.replace("-", "");
    }


}
