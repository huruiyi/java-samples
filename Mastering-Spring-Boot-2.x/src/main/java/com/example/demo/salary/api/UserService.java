package com.example.demo.salary.api;

import com.example.demo.chapter_2.domain.SalaryAndUser;
import com.example.demo.chapter_2.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
  List<User> getAllUsers();
  void insert(User user);
  Optional<SalaryAndUser> getSalaryAndUsersForUserId(String userId);
}
