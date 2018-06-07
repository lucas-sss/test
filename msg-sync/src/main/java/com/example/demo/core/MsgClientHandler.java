package com.example.demo.core;

import com.example.demo.msg.BusinessMsg;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/5 0005
 */
public class MsgClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BusinessMsg businessMsg = (BusinessMsg) msg;
        System.out.println("接受消息类型：" + businessMsg.getMsgType() + ",消息体：" + businessMsg.getMsgBody());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable var2) throws Exception{
        System.out.println("发生异常：" + var2.getMessage());
        ctx.close();
    }
}
