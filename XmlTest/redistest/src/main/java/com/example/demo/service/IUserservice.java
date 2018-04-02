package com.example.demo.service;

import com.example.demo.domain.User;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/28 0028
 */
public interface IUserservice {


    User findOneById(long id);

}
