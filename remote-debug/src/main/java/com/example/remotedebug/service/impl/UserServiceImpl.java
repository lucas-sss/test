package com.example.remotedebug.service.impl;

import com.example.remotedebug.dao.IUserDao;
import com.example.remotedebug.model.User;
import com.example.remotedebug.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/29 0029
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findOneById(Integer id) {
        return null == id ? null : userDao.findOneById(id);
    }

    @Override
    public int save(User user) {
        return userDao.save(user);
    }
}
