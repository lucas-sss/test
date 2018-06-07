package com.example.demo.msg;

import com.example.demo.core.ChannelContext;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class RegisteMsg extends BusinessMsg {

    private Channel channel;

    public RegisteMsg() {
        super();
        super.setMsgType(MsgTypeEnum.SERVER_REGISTE);
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void invoke() {
        //1、实现注册功能
        System.out.println("注册成功啦");
        InetSocketAddress remoteAddress = (InetSocketAddress) channel.remoteAddress();
        String clientIP = remoteAddress.getAddress().getHostAddress();
        System.out.println("clientIP=" + clientIP);

        Object msgBody = getMsgBody();

        ChannelContext.cache((String) msgBody, channel);
        //2、回复注册结果
        channel.write(("恭喜你注册成功啦" + System.getProperty("line.separator")).getBytes());
        channel.flush();

    }
}
