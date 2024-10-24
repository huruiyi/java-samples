package com.example.thread.threadpool.pool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyDynamicThreadPool2 {

  private int maxThreadsSize = 4;

  private int coreThreadsSize = 4;

  private int taskQueueSize = 20;

  private int currThreadSize = 0;

  private BlockingQueue<Runnable> taskQueue;

  private List<Worker> workers;

  private int kxThreadCount = 0;

  private boolean working = true;

  public MyDynamicThreadPool2(int maxThreadsSize, int coreThreadsSize,
      int taskQueueSize) {
    super();
    if (taskQueueSize > 0) {
      this.taskQueueSize = taskQueueSize;
    }
    taskQueue = new ArrayBlockingQueue<>(this.taskQueueSize);
    if (maxThreadsSize > 0) {
      this.maxThreadsSize = maxThreadsSize;
    }
    if (coreThreadsSize > 0) {
      this.coreThreadsSize = coreThreadsSize;
      if (this.maxThreadsSize < this.coreThreadsSize) {
        this.maxThreadsSize = this.coreThreadsSize;
      }
    }

    this.workers = Collections.synchronizedList(new LinkedList<>());

    for (int i = 0; i < this.coreThreadsSize; i++) {
      new Worker(taskQueue, this, null).start();
    }
    this.currThreadSize = this.coreThreadsSize;
  }

  public static void main(String[] args) {
    MyDynamicThreadPool2 pool = new MyDynamicThreadPool2(10, 5, 10);
    pool.printPoolInfo();
    for (int i = 0; i < 18; i++) {
      pool.submit(new Runnable() {

        @Override
        public void run() {
          try {
            Thread.sleep(30000L);
            System.out.println(
                Thread.currentThread().getName() + "任务完成！");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      System.out.println("------------- i=" + i);
      pool.printPoolInfo();
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
      synchronized (this) {
        if (this.kxThreadCount == 0) {
          if (this.currThreadSize < this.maxThreadsSize) {
            new Worker(taskQueue, this, task).start();
            this.currThreadSize++;

            return true;
          }
        }
      }
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

  public synchronized void printPoolInfo() {
    System.out.println("*******************线程池[" + this + "]当前状态:");
    System.out.println("当前池中线程数：" + this.currThreadSize);
    System.out.println("空闲线程数：" + this.kxThreadCount);
    System.out.println("等待执行任务数：" + this.taskQueue.size());
  }

  private class Worker extends Thread {

    private BlockingQueue<Runnable> taskQueue;

    private MyDynamicThreadPool2 pool;

    private boolean kx = true;

    private int doTaskCount = 0;

    private Runnable firstTask = null;

    public Worker(BlockingQueue<Runnable> taskQueue,
        MyDynamicThreadPool2 pool, Runnable firstTask) {
      super();
      this.taskQueue = taskQueue;
      this.pool = pool;
      pool.workers.add(this);
      synchronized (pool) {
        pool.kxThreadCount++;
      }
      this.firstTask = firstTask;
    }

    public void run() {
      while (this.pool.working || taskQueue.size() > 0) {
        Runnable task = null;
        if (this.firstTask != null) {
          task = firstTask;
          this.firstTask = null;
        } else {
          task = taskQueue.poll();
        }

        if (task == null) {
          if (!this.kx) {
            this.kx = true;
            synchronized (this.pool) {
              this.pool.kxThreadCount++;
            }
          }
          // 等待60秒
          try {
            task = taskQueue.poll(60, TimeUnit.SECONDS);
          } catch (InterruptedException e1) {
            System.out.println(
                Thread.currentThread().getName() + " 被中断。");
          }

          if (task == null) {
            // 判断当前线程数是否大于核心线程数，是的则结束该线程
            synchronized (this.pool) {
              if (this.pool.currThreadSize > this.pool.coreThreadsSize) {
                break;
              }
            }

            // 继续等待
            try {
              task = taskQueue.take();
            } catch (InterruptedException e) {
              System.out.println(Thread.currentThread().getName()
                  + "从等待中被中断！");
            }
          }

        }

        if (task != null) {
          if (this.kx) {
            this.kx = false;
            synchronized (this.pool) {
              this.pool.kxThreadCount--;
            }
          }
          try {
            task.run();
          } catch (Exception e) {
            e.printStackTrace();
          }

          System.out.println(Thread.currentThread().getName()
              + "累计完成第" + (++this.doTaskCount) + "个任务");
        }

      }

      pool.workers.remove(this);
      synchronized (this.pool) {
        this.pool.currThreadSize--;
        if (this.kx) {
          this.pool.kxThreadCount--;
        }
      }
      System.out.println(Thread.currentThread().getName() + "---结束。池中剩余"
          + this.pool.currThreadSize);
    }
  }
}
