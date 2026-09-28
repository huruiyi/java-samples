package com.example.service;

//@Component
public class HelloPrintImpl implements HelloPrint {

  @Override
  public void printStr(String str1, String str2) {
    System.out.println("Hello " + str1 + " , " + str2);
    //throw new RuntimeException("测试异常");
  }

}
