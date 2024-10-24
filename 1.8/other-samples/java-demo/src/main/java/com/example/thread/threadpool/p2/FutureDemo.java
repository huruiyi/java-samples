package com.example.thread.threadpool.p2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

  public static void main(String[] args) {

    ExecutorService pool = Executors.newFixedThreadPool(3);

    Future<String> fu = pool.submit(new Callable<String>() {

      @Override
      public String call() throws Exception {
        Thread.sleep(10000L);
        return "aaaaaaaaaaa";
      }

    });

    // 然后再看任务执行完了没，执行完了就去的结果，再接着干点活。。
    System.out.println(fu.isDone());

    fu.cancel(true);

    System.out.println(fu.isCancelled());

    try {
      String res = fu.get();
      System.out.println(res);
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("sssssssssssssssss");
  }
}
