package com.example;

public interface DubboRemoteService {

  /**
   * 远程调用接口方法
   *
   * @param name
   * @return
   */
  String call(String name);
}
