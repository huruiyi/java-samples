package com.example.service;

import com.example.pojo.Info;

import java.util.List;

public interface InfoService {


  List<Info> findAll();

  int insert(Info info);

  int jdbcInsert(Info info);

}
