package com.example.mapper;

import com.example.model.User;

import java.util.List;

public interface UserMapper {

  User selectByPrimaryKey(Integer id);

  List<User> selectAllUsers();
}
