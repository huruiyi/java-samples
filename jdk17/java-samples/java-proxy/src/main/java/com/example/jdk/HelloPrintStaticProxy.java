package com.example.jdk;

import com.example.service.HelloPrint;

public class HelloPrintStaticProxy implements HelloPrint {

  private HelloPrint target;

  public HelloPrintStaticProxy(HelloPrint target) {
    this.target = target;
  }

  @Override
  public void printStr(String str1, String str2) {
    System.out.println("[STATIC PROXY] 开始调用");
    target.printStr(str1, str2);
    System.out.println("[STATIC PROXY] 调用结束");
  }

}
