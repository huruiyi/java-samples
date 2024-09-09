package vip.fairy.sample_strategy;

public class App {

  public static void main(String[] args) {
    Environment environment=new Environment(new AddFunc());
    System.out.println(environment.calculate(100,50));
    environment=new Environment(new SubFunc());
    System.out.println(environment.calculate(100,50));
  }
}
