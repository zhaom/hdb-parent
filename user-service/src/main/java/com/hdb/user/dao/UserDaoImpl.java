package com.hdb.user.dao;

import com.haodaibao.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2014/12/17.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByUserName(String appkey, String username) {

        String sql = "select id, uname, pwd, locked from member where uname=? and register_platform=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username, appkey);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User createUser(User user) {
        String sql = "insert into member(uname, pwd) value('"+user.getUname()+"',"+user.getPwd()+"')";
        jdbcTemplate.execute(sql);
        return user;
    }
}
