package com.in28minutes.repository;

import com.in28minutes.SpringDataJpaFirstExampleApplication;
import com.in28minutes.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest(classes = SpringDataJpaFirstExampleApplication.class)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void testing_sort_stuff() {
    Sort sort = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.DESC, "userid"));
    Iterable<User> users = userRepository.findAll(sort);

    for (User user : users) {
      System.out.println(user);
    }
  }

  @Test
  public void using_pageable_stuff() {
    PageRequest pageable = PageRequest.of(0, 2);
    Page<User> userPage = userRepository.findAll(pageable);

    System.out.println(userPage);
    System.out.println(userPage.getContent());
  }

}