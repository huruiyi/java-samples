package com.example.demoC;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextC.xml")
public class SpringDemoC {

  @Resource(name = "orderDao")
  private OrderDao orderDao;

  @Test
  public void demo1() {
    System.out.println("*******************************************************************");
    orderDao.save();
    System.out.println("*******************************************************************");
    orderDao.delete();
    System.out.println("*******************************************************************");
    orderDao.update();
    System.out.println("*******************************************************************");
    orderDao.find();
    System.out.println("*******************************************************************");
  }
}
