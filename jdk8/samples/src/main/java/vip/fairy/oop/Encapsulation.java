package vip.fairy.oop;

public class Encapsulation {

  public static void main(String[] args) {

    BankAccount a = new BankAccount();

    a.deposit(1500);

    if (a.withdraw(24)) {
      System.out.println("OK");
    } else {
      System.out.println("NOT ENOUGH MONEY");
    }

    System.out.println(a.getBalance());

  }

  static class BankAccount {

    private int balance;

    public BankAccount() {
      balance = 1000;
    }

    int getBalance() {
      return balance;
    }

    private void setBalance(int amount) {
      balance = amount;
    }

    boolean withdraw(int amount) {
      if (balance > amount) {
        setBalance(balance - amount);
      } else {
        return false;
      }
      return true;
    }

    boolean deposit(int amount) {
      setBalance(balance + amount);
      return true;
    }
  }

}

