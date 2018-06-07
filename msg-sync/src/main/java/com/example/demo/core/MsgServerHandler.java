package com.example.demo.core;

import com.example.demo.common.MsgType;
import com.example.demo.msg.BusinessMsg;
import com.example.demo.msg.RegisteMsg;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/5 0005
 */
public class MsgServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        BusinessMsg businessMsg = (BusinessMsg) msg;

        String msgType = businessMsg.getMsgType();

        if (MsgType.REGISTER.equals(msgType)){
            RegisteMsg registeMsg = new RegisteMsg();
            registeMsg.setMsgBody(businessMsg.getMsgBody());
            registeMsg.setChannel(ctx.channel());
            registeMsg.invoke();
            return;
        }
        System.out.println("接受消息类型：" + businessMsg.getMsgType() + ", body:" + businessMsg.getMsgBody());
        /*String body = (String) msg;
        System.out.println("server recive body:" + body + ",count=" + count++);
        ByteBuf resp = Unpooled.copiedBuffer((body + System.getProperty("line.separator")).getBytes());
        ctx.writeAndFlush(resp);*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable var2) throws Exception{
        System.out.println("发生异常：" + var2.getMessage());
        ctx.close();
    }
}
