package vip.fairy.generictype.oopa;


class BankAccount {

  private double accountBalance;

  public BankAccount(double accountBalance) {
    this.accountBalance = accountBalance;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void calculateInterest(final double interestRate) {

    class Interest {

      private double interestRate;

      public Interest() {
        this.interestRate = interestRate;
      }

      public double getInterestRate() {
        return interestRate;
      }

      public void changeAccountBalance() {
        double interest = (accountBalance * interestRate) / 100;
        accountBalance += interest;
      }
    }

    Interest interest = new Interest();
    interest.changeAccountBalance();
  }

}
