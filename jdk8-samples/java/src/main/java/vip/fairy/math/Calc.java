package vip.fairy.math;

/**
 * 数学计算 -encoding UTF-8 -charset UTF-8
 */
public class Calc {

  /**
   * 计算两个数的和
   *
   * @param a 第一个参数
   * @param b 第二个参数
   * @return 返回两个数的和
   */
  public static int Add(int a, int b) {
    return a + b;
  }

  /**
   * 找两个数的最大值
   *
   * @param a 第一个参数
   * @param b 第二个参数
   * @return 返回最大值
   */
  public static int GetMax(int a, int b) {
    return a > b ? a : b;
  }

  /**
   * 计算两个数的差
   *
   * @param a 第一个参数
   * @param b 第二个参数
   * @return 返回a-b的值
   */
  public static int Subtract(int a, int b) {
    return a - b;
  }

  /**
   * 计算两个数的积
   *
   * @param a 第一个参数
   * @param b 第二个参数
   * @return 返回两个数的乘积
   */
  public static int Multiply(int a, int b) {
    return a * b;
  }

  /**
   * 计算两个数的倍数
   *
   * @param a 第一个参数
   * @param b 第二个参数
   * @return 返回a/b的结果
   */
  public static int Divide(int a, int b) {
    return a / b;
  }
}
