package com.example.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  Logger logger = Logger.getLogger(HelloService.class);

  public void sayHi(String userName) {
    logger.info("你好啊，" + userName);
  }

}
