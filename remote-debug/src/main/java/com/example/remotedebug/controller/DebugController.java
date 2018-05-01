package com.example.remotedebug.controller;

import com.example.remotedebug.model.User;
import com.example.remotedebug.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/29 0029
 */
@RestController
public class DebugController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/testRemoteDebug/{id}")
    public String testRemoteDebug(@PathVariable("id")Integer id) {

        User user = userService.findOneById(id);
        if (user == null){
            return "无数据";
        }
        return user.toString();
    }


    @RequestMapping("/save/{name}/{age}")
    public String saveUser(@PathVariable("name")String name, @PathVariable("age")Integer age){
        User save = new User();
        save.setName(name);
        save.setAge(age);
        userService.save(save);
        return "已保存：" + save.toString();
    }
}
