package com.example.thread.threadpool.p3;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedSizeThreadPool {

  // 仓库
  private BlockingQueue<Runnable> taskQueue;

  // 工作线程
  private List<Worker> workers;

  // 线程池工作的标识
  private volatile boolean working = true;

  public FixedSizeThreadPool(int poolSize, int taskQueueSize) {
    if (poolSize <= 0 || taskQueueSize <= 0) {
      throw new IllegalArgumentException("参数错误");
    }

    taskQueue = new LinkedBlockingQueue<>(taskQueueSize);

    this.workers = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < poolSize; i++) {
      Worker w = new Worker(this);
      w.start();
      this.workers.add(w);
    }
  }

  public static void main(String[] args) {
    FixedSizeThreadPool pool = new FixedSizeThreadPool(3, 6);

    for (int i = 0; i < 6; i++) {
      pool.submit(new Runnable() {

        @Override
        public void run() {
          System.out.println("任务开始执行。。。。。");
          try {
            Thread.sleep(2000L);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
    }

    pool.shutdown();
  }

  public boolean submit(Runnable task) {
    if (this.working) {
      return this.taskQueue.offer(task);
    }

    return false;
  }

  // 提供别的可以阻塞、阻塞等待一段时间的方法。

  public void shutdown() {
    this.working = false;

    // 把阻塞的线程中断
    for (Thread t : this.workers) {
      if (t.getState().equals(State.BLOCKED)
          || t.getState().equals(State.WAITING)
          || t.getState().equals(State.TIMED_WAITING)) {
        t.interrupt();
      }
    }
  }

  private static class Worker extends Thread {

    private FixedSizeThreadPool pool;

    public Worker(FixedSizeThreadPool pool) {
      super();
      this.pool = pool;
    }

    public void run() {
      int taskCount = 0;
      while (this.pool.working || this.pool.taskQueue.size() > 0) {
        Runnable task = null;

        try {
          if (this.pool.working) {
            task = this.pool.taskQueue.take();
          } else {
            task = this.pool.taskQueue.poll();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        if (task != null) {
          task.run();
          System.out.println(Thread.currentThread().getName() + "执行完"
              + (++taskCount) + "个任务！");
        }
      }

      System.out.println(Thread.currentThread().getName() + "结束");
    }
  }
}
