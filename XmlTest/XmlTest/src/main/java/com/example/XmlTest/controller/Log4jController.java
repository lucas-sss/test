package com.example.XmlTest.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/31 0031
 */
@RestController
public class Log4jController {

    public static final Logger LOGGER = Logger.getLogger(Log4jController.class.getName());


    @RequestMapping("/log4j")
    public String testLog4j() {

        LOGGER.info("测试log4j");

        return "log4j";
    }

}
