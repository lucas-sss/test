package com.example.XmlTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/31 0031
 */
@RestController
public class JdkLogController {

    public static final Logger log = Logger.getLogger(JdkLogController.class.getName());


    @RequestMapping("/jdklog")
    public String testCommonLog(){
        log.setLevel(Level.ALL);
        log.info("测试jdklog");

        return "测试jdklog";
    }
}
