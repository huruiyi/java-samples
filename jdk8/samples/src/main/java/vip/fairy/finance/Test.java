package vip.fairy.finance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

  static void test1() {
    BigDecimal a = BigDecimal.valueOf(68)
        .multiply(BigDecimal.valueOf(5600))
        .multiply(BigDecimal.valueOf(180))
        .divide(BigDecimal.valueOf(1000), 3, RoundingMode.HALF_UP);
    BigDecimal b = BigDecimal.valueOf(103390).add(a);
    BigDecimal extPerTonOilPrice = b.divide(BigDecimal.valueOf(2700), 3, RoundingMode.HALF_UP);
    System.out.println(extPerTonOilPrice);
  }

  public static void main(String[] args) {
    test1();
  }

}
