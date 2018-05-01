package com.example.remotedebug.dao;

import com.example.remotedebug.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/30 0030
 */
@Repository
public interface IUserDao {
    User findOneById(Integer id);

    int save(User user);
}
