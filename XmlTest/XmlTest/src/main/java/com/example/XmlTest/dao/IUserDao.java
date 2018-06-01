package com.example.XmlTest.dao;


import com.example.XmlTest.domain.User;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/25 0025
 */
public interface IUserDao {

    User selectOneById(Long id);
}
