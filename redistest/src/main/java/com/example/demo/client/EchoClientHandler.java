package com.example.demo.client;

import com.example.demo.utils.MsgSendUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/5 0005
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private byte[] req = null;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MsgSendUtil.setContext(ctx);

        ByteBuf msg = null;
        for (int i = 0; i < 100; i++) {

            req = ("我发送消息" + i + System.getProperty("line.separator")).getBytes();
            msg = Unpooled.buffer(req.length);
            msg.writeBytes(req);
            ctx.writeAndFlush(msg);
        }

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String resp = (String) msg;

        System.out.println("client recive body:" + resp);
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable var2) throws Exception{
        System.out.println("发生异常：" + var2.getMessage());
        ctx.close();
    }
}
