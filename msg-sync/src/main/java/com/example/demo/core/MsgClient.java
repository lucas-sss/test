package com.example.demo.core;

import com.example.demo.utils.MsgSendUtil;
import com.example.demo.utils.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/1 0001
 */
@Component
public class MsgClient {

    private String host = "127.0.0.1";

    private Integer port = 8080;

    private Channel channel;

    private Object lock = new Object();

    private EventLoopGroup group;


    public void connect(){

        group = new NioEventLoopGroup();

        Bootstrap bs = new Bootstrap();
        bs.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder())
                                .addLast(new EchoClientHandler());
                        channel = socketChannel;
                    }
                });

        Thread thread = new Thread(() -> {

            while (true){
                System.out.println("循环进行时");
                //线程链接成功则进行等待
                if (channel != null && channel.isActive()){
                    System.out.println("即将进行锁判断");
                    synchronized (lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ChannelFuture connect = bs.connect(host, port);
                channel = connect.channel();
                MsgUtil.loadChannel(channel);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("进行重连啦");
            }
        });

        thread.start();

        /*try {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }*/
    }


    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void reConnect(){
        synchronized (lock){
            lock.notifyAll();
        }
    }

    public void close(){
        group.shutdownGracefully();
    }

    @PostConstruct
    public void init(){
        new MsgClient().connect();
    }
}
