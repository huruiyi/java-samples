package com.example.tx.demoH;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextH.tx.xml")
public class SpringDemoTx2 {

  @Resource(name = "accountService")
  private AccountService accountServicea;

  @Test
  public void demo1() {
    accountServicea.transfer("如花", "小宝", 1000d);
  }
}
