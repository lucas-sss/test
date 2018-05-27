package com.example.demo.utils;

import com.example.demo.core.MsgClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/1 0001
 */
@Component
public class MsgUtil {

    @Autowired
    private MsgClient msgClient;

    private static final Random random = new Random();

    private static final UUID uuid = UUID.randomUUID();

    public static String getUuid(){
        String s = uuid.toString();
        return s.replace("-", "");
    }
    private static Channel channel;

    public static void loadChannel(Channel ch){
        channel = ch;
    }

    public static Object send(Object msg){

        try {
            if (channel != null && channel.isActive()){
                byte[] biteMsg = (msg.toString() + System.getProperty("line.separator")).getBytes();
                ByteBuf buf = Unpooled.buffer(biteMsg.length);
                buf.writeBytes(biteMsg);
                channel.writeAndFlush(buf);
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
