package com.example.concurrent;

import java.util.stream.LongStream;

/**
 * 作者：胡睿毅 文件名：PrimeNumbers 日期：2019/5/26 15:00
 **/
class PrimeNumbers {

  private static boolean isPrime(long val) {
    for (long i = 2; i <= val / 2; i++) {
      if ((val % i) == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    long numOfPrimes = LongStream.rangeClosed(2, 100_000).filter(PrimeNumbers::isPrime).count();
    System.out.println(numOfPrimes);
  }
}
