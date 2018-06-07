package com.example.demo.client;

import com.example.demo.config.ParamConfig;
import com.example.demo.core.coder.MsgJsonDecoder;
import com.example.demo.core.coder.MsgJsonEncoder;
import com.example.demo.utils.MsgHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/1 0001
 */
@Service
public class MsgClient {

    private String host = "10.1.202.243";

    private Integer port = 9002;

    private Channel channel;

    private static Object lock = new Object();

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
//                                .addLast(new LineBasedFrameDecoder(1024))
//                                .addLast(new StringDecoder())
                                .addLast(new MsgJsonDecoder())
                                .addLast(new MsgJsonEncoder())
                                .addLast(new MsgClientHandler());
                        channel = socketChannel;
                    }
                });

        new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(5);
                }catch (Exception e){

                }
                ChannelFuture connect = bs.connect(host, port);
                channel = connect.channel();
            }
            try {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            /*while (true){
                //线程链接成功则进行等待
                if (channel != null && channel.isActive()){
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
                MsgHandler.loadChannel(channel);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("进行重连啦");
            }*/
        }).start();
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

    public static void main(String[] args) {
        new MsgClient().connect();
    }
}
