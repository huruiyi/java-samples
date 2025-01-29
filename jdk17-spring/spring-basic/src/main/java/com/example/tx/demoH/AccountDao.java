package com.example.tx.demoH;

public interface AccountDao {

  void outMoney(String from, Double money);

  void inMoney(String to, Double money);
}
