package com.example.generictype.oopa;

public class App {

  public static void main(String[] args) {
    BankAccount bankAccount = new BankAccount(1000);

    System.out.println(bankAccount.getAccountBalance());

    bankAccount.calculateInterest(5);

    System.out.println(bankAccount.getAccountBalance());

    bankAccount.calculateInterest(5);

    System.out.println(bankAccount.getAccountBalance());

    bankAccount.calculateInterest(5);

    System.out.println(bankAccount.getAccountBalance());
  }
}
