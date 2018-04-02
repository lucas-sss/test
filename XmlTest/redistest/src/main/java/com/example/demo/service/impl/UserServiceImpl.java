package com.example.demo.service.impl;

import com.example.demo.dao.IUserDao;
import com.example.demo.domain.User;
import com.example.demo.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/28 0028
 */
@Service
public class UserServiceImpl implements IUserservice {

    @Autowired
    private IUserDao userDao;


    @Override
    public User findOneById(long id) {
        User query = new User();
        query.setId(id);

        return userDao.selectOne(query);
    }
}
