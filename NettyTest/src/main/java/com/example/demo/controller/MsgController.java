package com.example.demo.controller;

import com.example.demo.utils.MsgSendUtil;
import org.springframework.stereotype.Controller;
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
    public Object sendMsg(String msg){

        MsgSendUtil.send(msg);

        return 345;
    }

}
