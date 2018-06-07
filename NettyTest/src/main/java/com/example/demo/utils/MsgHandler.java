package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class MsgHandler {

    private static Channel channel;

    public static void loadChannel(Channel ch){
        channel = ch;
    }

    public static Object send(Object msg){

        try {
            if (channel != null && channel.isActive()){
//                byte[] biteMsg = (msg.toString() + System.getProperty("line.separator")).getBytes();
//                ByteBuf buf = Unpooled.buffer(biteMsg.length);
//                buf.writeBytes(biteMsg);
                channel.writeAndFlush(msg);
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }


    public static Object send(Channel ch, Object msg){
        try {

            if (ch != null && ch.isActive()){
//                byte[] biteMsg = (msg.toString() + System.getProperty("line.separator")).getBytes();
//                ByteBuf buf = Unpooled.buffer(biteMsg.length);
//                buf.writeBytes(biteMsg);

                ch.writeAndFlush(msg);
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
