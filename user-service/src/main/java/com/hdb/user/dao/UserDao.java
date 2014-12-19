package com.hdb.user.dao;

import com.haodaibao.user.entity.User;

/**
 * Created by user on 2014/12/17.
 */
public interface UserDao {


    User findByUserName(String appkey, String username);

    User createUser(User user);
}
