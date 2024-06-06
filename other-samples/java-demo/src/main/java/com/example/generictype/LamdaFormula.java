package com.example.generictype;


public class LamdaFormula {

  public static void main(String[] args) {
    Formula f = (int a) -> {
      return 5;
    };

    double calculate = f.calculate(36);
    System.out.println(calculate);

    double sqrt = f.sqrt(49);
    System.out.println(sqrt);
  }

  interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
      return Math.sqrt(a);
    }
  }

  class A implements Formula {

    @Override
    public double calculate(int a) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

  }
}


