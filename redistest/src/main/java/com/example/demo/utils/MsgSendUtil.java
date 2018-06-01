package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/26 0026
 */
public class MsgSendUtil {

    public static ChannelHandlerContext channelHandlerContext;

    private static volatile boolean connecting = false;

    public static void setConnecting(boolean flag){
        connecting = flag;
    }

    public static void setContext(ChannelHandlerContext context){
        channelHandlerContext = context;
    }

    public static Object send(Object msg){

        try {
            if (channelHandlerContext != null && !connecting){

                byte[] req = (msg.toString() + System.getProperty("line.separator")).getBytes();
                ByteBuf bf = Unpooled.buffer(req.length);
                bf.writeBytes(req);
                channelHandlerContext.writeAndFlush(bf);
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
