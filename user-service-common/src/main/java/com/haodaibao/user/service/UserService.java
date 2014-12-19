package com.haodaibao.user.service;


import com.haodaibao.user.entity.User;

import java.util.List;

/**
 * Created by user on 2014/12/16.
 */
public interface UserService {

    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);

    public void changePassword(Long userId, String newPassword);


    User findOne(Long userId);

    List<User> findAll();

    public User findByUsername(String appkey, String username);
}
