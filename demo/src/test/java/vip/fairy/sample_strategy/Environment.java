package vip.fairy.sample_strategy;

public class Environment {


  private   Strategy strategy;

  public Environment(Strategy strategy) {
    this.strategy = strategy;
  }

  public int calculate(int a, int b) {
    return strategy.calculate(a, b);
  }
}
