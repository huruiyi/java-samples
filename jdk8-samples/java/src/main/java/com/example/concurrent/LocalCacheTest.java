package com.example.concurrent;

import java.util.concurrent.CountDownLatch;

public class LocalCacheTest {

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    LocalCache localCache = new LocalCache();
    int n = 500; //线程数
    int m = 100000; //每个线程put个数
    CountDownLatch count = new CountDownLatch(n);
    for (int i = 0; i < n; i++) {
      new Thread(() -> {
        for (int j = 0; j < m; j++) {
          localCache.put(j + "", new Object(), 10);
        }
        count.countDown();
      }).start();
    }
    count.await();
    System.out.println("size:" + localCache.cache.size());
    System.out.println("cur:" + localCache.cur);
    System.out.println("耗时  " + (System.currentTimeMillis() - start));
  }
}
