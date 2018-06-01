package com.example.XmlTest.controller;

import com.example.XmlTest.domain.User;
import com.example.XmlTest.service.IUserservice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/31 0031
 */
@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);


    @Autowired
    private IUserservice userservice;

    @RequestMapping("/user/{id}")
    public String getAllUser(@PathVariable("id") Long id) {

        User find = userservice.findOneById(id);


        LOGGER.info("查询用户:" + find.toString());

        return find.toString();

    }
}
