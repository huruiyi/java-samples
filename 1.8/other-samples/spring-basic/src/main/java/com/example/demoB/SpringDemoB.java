package com.example.demoB;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextB.xml")
public class SpringDemoB {

  @Resource(name = "productDao")
  private ProductDao productDao;

  @Test
  public void demo1() {
    productDao.save();
    productDao.update();
    productDao.delete();
    productDao.find();
  }
}
