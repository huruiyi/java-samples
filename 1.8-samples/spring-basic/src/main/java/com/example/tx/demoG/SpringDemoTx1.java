package com.example.tx.demoG;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextG.tx.xml")
public class SpringDemoTx1 {

  @Resource(name = "accountService")
  private AccountService accountServicea;

  @Test
  public void demo1() {
    accountServicea.transfer("如花", "小宝", 3000d);
  }
}
