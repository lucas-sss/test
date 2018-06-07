package com.example.demo.controller;

import com.example.demo.common.MsgType;
import com.example.demo.msg.RegisteMsg;
import com.example.demo.utils.ClientCache;
import com.example.demo.utils.MsgHandler;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/26 0026
 */
@RestController
public class MsgController {


    @RequestMapping("/sendMsg")
    public Object sendMsg(String msg, String client){

        Channel ch = ClientCache.search(client);

        if (ch != null){
            RegisteMsg registeMsg = new RegisteMsg();
            registeMsg.setMsgType(MsgType.KEY_SEARCH_ACK);
            registeMsg.setMsgBody(client + "-" + msg);

            Object send = MsgHandler.send(ch, registeMsg);
            if (send != null) {
                return "链接断开";
            }
            return "发送成功";
        }

        return "没有此client：" + client;
    }

}
