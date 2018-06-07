package com.example.demo.controller;

import com.example.demo.common.MsgType;
import com.example.demo.core.ChannelContext;
import com.example.demo.core.MsgClient;
import com.example.demo.msg.RegisteMsg;
import com.example.demo.utils.MsgHandler;
import com.example.demo.utils.MsgUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/26 0026
 */
@RestController
public class MsgController {
    @Autowired
    MsgClient msgClient;

    @RequestMapping("/sendMsg")
    public Object sendMsg(String msg) {
        RegisteMsg registeMsg = new RegisteMsg();
        registeMsg.setMsgBody(msg);
        Object send = MsgHandler.send(registeMsg);
        if (send != null) {
            msgClient.reConnect();
        }
        return msg;
    }


    @RequestMapping("/sendMsgToAllClient")
    public Object sendMsgToAllClient(String msg) {

        if (ChannelContext.size() > 0){
            Set<Map.Entry<String, Channel>> entries = ChannelContext.entries();

            for (Map.Entry<String, Channel> entry : entries) {
                RegisteMsg registeMsg = new RegisteMsg();
                registeMsg.setMsgType(MsgType.KEY_SEARCH);
                registeMsg.setMsgBody(entry.getKey() + "-" + msg);
                System.out.println("即将发送消息：" + registeMsg.getMsgBody());
                MsgHandler.send(entry.getValue(), registeMsg);
            }
        }
        return msg;
    }

    @RequestMapping("/sendMsgToClient")
    public Object sendMsgToClient(String msg, String lable) {

        if (ChannelContext.size() > 0){
            ConcurrentHashMap<String, Channel> cctx = ChannelContext.getCctx();

            if (cctx.size() > 0 && null != lable){

                if (cctx.containsKey(lable)){
                    RegisteMsg registeMsg = new RegisteMsg();
                    registeMsg.setMsgType(MsgType.KEY_SEARCH);
                    registeMsg.setMsgBody(lable + "-" +  msg);
                    MsgHandler.send(cctx.get(lable), registeMsg);
                }

            }
        }
        return msg;
    }

}
