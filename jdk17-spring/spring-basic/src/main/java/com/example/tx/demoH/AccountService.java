package com.example.tx.demoH;

public interface AccountService {

  /**
   * @param from
   * @param to
   * @param money
   */
  void transfer(String from, String to, Double money);
}
