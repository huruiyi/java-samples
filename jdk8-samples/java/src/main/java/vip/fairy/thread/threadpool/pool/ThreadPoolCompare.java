package vip.fairy.thread.threadpool.pool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolCompare {

  static void useThreadPool(int jobCount, int segmentSize, int[] iarray,
      int bound, CountDownLatch cdl) {

    // 创建一个线程池来执行任务
    ExecutorService tpool = Executors.newFixedThreadPool(6);

    for (int i = 0; i < jobCount; i++) {
      tpool.execute(new InitWorker(i, iarray, i * segmentSize,
          (i + 1) * segmentSize - 1, bound, cdl));
    }

    // 线程池不需要用了，把它关闭
    tpool.shutdown();
  }

  static void directUseThread(int jobCount, int segmentSize, int[] iarray,
      int bound, CountDownLatch cdl) {
    for (int i = 0; i < jobCount; i++) {
      new Thread(new InitWorker(i, iarray, i * segmentSize,
          (i + 1) * segmentSize - 1, bound, cdl)).start();
      ;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // 数组大小
    int size = 100_000_000;
    final int[] iarray = new int[size];
    // 随机数范围
    int bound = 10000;
    // 分段大小
    int segmentSize = 10_000;
    // 分成的任务个数
    int jobCount = size / segmentSize;

    // 记录一下等待开始时间
    long s1 = System.currentTimeMillis();

    // 创建一个倒计数锁存器来进行协同控制，计数值为分成的任务数 jobCount
    CountDownLatch cdl = new CountDownLatch(jobCount);

    // ThreadPoolCompare.directUseThread(jobCount, segmentSize, iarray,
    // bound,
    // cdl);

    ThreadPoolCompare.useThreadPool(jobCount, segmentSize, iarray, bound,
        cdl);

    // 协同：让main线程等待任务都完成
    cdl.await();

    System.out.println("耗时:" + (System.currentTimeMillis() - s1));

  }

  static class InitWorker implements Runnable {

    // 任务编号
    int taskNo;
    // 要初始化的数组
    int[] arr;
    // 负责初始化的范围
    int startIndex, endIndex;
    // 生成随机整数的范围
    int bound;

    // 协同用的倒计数锁存器
    CountDownLatch cdl;

    public InitWorker(int taskNo, int[] arr, int startIndex, int endIndex,
        int bound, CountDownLatch cdl) {
      super();
      this.taskNo = taskNo;
      this.arr = arr;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
      this.bound = bound;
      this.cdl = cdl;
    }

    public void run() {
      Random rd = new Random();
      for (int i = startIndex; i <= endIndex; i++) {
        arr[i] = rd.nextInt(bound);
      }
      System.out.println("任务-" + taskNo + " 完成");
      // 调用countDown()报告完成任务,锁存器的计数减一
      cdl.countDown();
    }
  }

}
