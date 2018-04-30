package com.example.remotedebug.service;

import com.example.remotedebug.model.User;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/29 0029
 */
public interface IUserService {

    User findOneById(Integer id);
}
