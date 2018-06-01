package com.example.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/5 0005
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    private int count;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;

        System.out.println("server recive body:" + body + ",count=" + count++);



        ByteBuf resp = Unpooled.copiedBuffer((body + System.getProperty("line.separator")).getBytes());

        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable var2) throws Exception{
        System.out.println("发生异常：" + var2.getMessage());
        ctx.close();
    }
}
