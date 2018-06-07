package com.example.demo.core;

import com.example.demo.core.coder.MsgJsonDecoder;
import com.example.demo.core.coder.MsgJsonEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/5 0005
 */
@Component
public class MsgServer {

    public void bind() {

        new Thread(() -> {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap sb = new ServerBootstrap();
                sb.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                        .option(ChannelOption.TCP_NODELAY, true)
                        //保持长连接状态
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        .childHandler(new ChannelInitializer<SocketChannel>() {

                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline()
                                        .addLast(new MsgJsonEncoder())
                                        .addLast(new MsgJsonDecoder())
                                        .addLast(new MsgServerHandler());
                            }
                        });

                ChannelFuture f = sb.bind(9002).sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }

        }).start();

    }

    @PostConstruct
    public void init() {
        new MsgServer().bind();
    }

    public static void main(String[] args) {
        new MsgServer().bind();
    }
}
