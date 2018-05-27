package com.example.demo.controller;

import com.example.demo.core.MsgClient;
import com.example.demo.utils.MsgSendUtil;
import com.example.demo.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Object send = MsgUtil.send(msg);
        if (send != null) {
            msgClient.reConnect();
        }
//        MsgSendUtil.send(msg);
        return 123;
    }

}
