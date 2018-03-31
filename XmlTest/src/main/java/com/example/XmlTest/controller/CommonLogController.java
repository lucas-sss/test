package com.example.XmlTest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/31 0031
 */
@RestController
public class CommonLogController {

    public static final Log LOGGER  = LogFactory.getLog(CommonLogController.class.getName());

    @RequestMapping("/commonlog")
    public String testCommonLog(){

        LOGGER.info("测试commonlog");

        return "测试commonlog";
    }

}
