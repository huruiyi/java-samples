package vip.fairy;

import java.math.BigDecimal;

public class Main {

  public static final void main(String[] args) throws Exception {

    CalculationService calculationService = new PlainCalculationService();
    for (int i = 0; i < 10; i++) {
      long start = System.currentTimeMillis();
      System.out.println(calculationService.heavyCalculation(BigDecimal.valueOf(2L), 16));
      long duration = System.currentTimeMillis() - start;
      System.out.println("Took: " + duration);
    }
  }
}
