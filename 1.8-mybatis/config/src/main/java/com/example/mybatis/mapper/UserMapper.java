package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;

import java.util.List;

/**
 * Created by lzc
 * 2020/1/1 16:02
 */
public interface UserMapper {

    public User getUserByUserName(String userName);

    public List<User> getUserList();

    public int addUser(User user);

    public int deleteUser(Integer id);

    public int updateUser(User user);
}
