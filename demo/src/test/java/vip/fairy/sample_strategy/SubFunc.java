package vip.fairy.sample_strategy;

public class SubFunc implements Strategy {

  @Override
  public int calculate(int x, int y) {
    return x - y;
  }
}
