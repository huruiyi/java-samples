package vip.fairy.sample_strategy;

public class AddFunc implements Strategy {

  @Override
  public int calculate(int x, int y) {
    return x + y;
  }
}
