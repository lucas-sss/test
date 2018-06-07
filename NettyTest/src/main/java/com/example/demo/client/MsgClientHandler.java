package com.example.demo.client;

import com.example.demo.common.Constants;
import com.example.demo.msg.BusinessMsg;
import com.example.demo.msg.RegisteMsg;
import com.example.demo.utils.ClientCache;
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
        String lable = Constants.clientLable.incrementAndGet() + "";
        ClientCache.add(lable, ctx.channel());

        RegisteMsg registeMsg = new RegisteMsg();
        registeMsg.setMsgBody(lable);

//        byte[] req = ("channel_lable" + System.getProperty("line.separator")).getBytes();
//        ByteBuf msg = Unpooled.buffer(req.length);
//        msg.writeBytes(req);
        ctx.writeAndFlush(registeMsg);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.toString());
        BusinessMsg businessMsg = (BusinessMsg) msg;
        System.out.println("接受消息类型：" + businessMsg.getMsgType() + ",消息体：" + businessMsg.getMsgBody());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable var2) throws Exception{
        System.out.println("发生异常：" + var2.getMessage());
        ctx.close();
    }
}
