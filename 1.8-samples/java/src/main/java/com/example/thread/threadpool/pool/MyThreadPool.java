package com.example.thread.threadpool.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {

  private int poolSize = 4;

  private int taskQueueSize = 20;

  private BlockingQueue<Runnable> taskQueue;

  private List<Worker> workers;

  private boolean working = true;

  public MyThreadPool(int poolSize, int taskQueueSize) {
    if (taskQueueSize > 0) {
      this.taskQueueSize = taskQueueSize;
    }
    taskQueue = new ArrayBlockingQueue<>(this.taskQueueSize);

    if (poolSize > 0) {
      this.poolSize = poolSize;
    }
    workers = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < this.poolSize; i++) {
      new Worker(taskQueue, this).start();
    }
  }

  public static void main(String[] args) {
    MyThreadPool pool = new MyThreadPool(5, 20);
    for (int i = 0; i < 18; i++) {
      pool.submit(new Runnable() {

        @Override
        public void run() {
          try {
            Thread.sleep(3000L);
            System.out.println(
                Thread.currentThread().getName() + "任务完成！");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    pool.shutdown();
  }

  public boolean submit(Runnable task) {
    if (this.working) {
      return this.taskQueue.offer(task);
    }
    return false;
  }

  public void shutdown() {
    this.working = false;
    for (Thread t : this.workers) {
      if (t.getState().equals(Thread.State.BLOCKED)
          || t.getState().equals(Thread.State.WAITING)) {
        t.interrupt();
      }
    }
  }

  private class Worker extends Thread {

    private BlockingQueue<Runnable> taskQueue;

    private MyThreadPool pool;

    public Worker(BlockingQueue<Runnable> taskQueue, MyThreadPool pool) {
      super();
      this.taskQueue = taskQueue;
      this.pool = pool;
      pool.workers.add(this);
    }

    public void run() {
      while (this.pool.working || taskQueue.size() > 0) {
        Runnable task = null;

        try {
          task = taskQueue.take();
        } catch (InterruptedException e1) {
          System.out.println(
              Thread.currentThread().getName() + " 从等待任务中被中断。");
        }

        if (task != null) {
          try {
            task.run();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

      }

      pool.workers.remove(this);
      System.out.println(Thread.currentThread().getName() + "---结束，池中剩余:"
          + pool.workers.size());
    }
  }
}
