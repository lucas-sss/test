package com.example.XmlTest.controller;

import com.example.XmlTest.domain.User;
import com.example.XmlTest.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/31 0031
 */
@RestController
public class UserController {

    @Autowired
    private IUserservice userservice;

    @RequestMapping("/user")
    public String getAllUser(){

        User find = userservice.findOneById(1L);

        return find.toString();

    }
}
