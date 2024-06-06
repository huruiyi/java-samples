package com.example.thread.threadpool.pool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyDynamicThreadPool {

  private int maxThreadsSize = 4;

  private int coreThreadsSize = 4;

  private int taskQueueSize = 20;

  private int currThreadSize = 0;

  private BlockingQueue<Runnable> taskQueue;

  private List<Worker> workingWorkers;

  private List<Worker> kxWorkers;

  private boolean working = true;

  public MyDynamicThreadPool(int maxThreadsSize, int coreThreadsSize,
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

    this.workingWorkers = Collections.synchronizedList(new LinkedList<>());
    this.kxWorkers = Collections.synchronizedList(new LinkedList<>());

    for (int i = 0; i < this.coreThreadsSize; i++) {
      new Worker(this, null).start();
    }
    this.currThreadSize = this.coreThreadsSize;
  }

  public static void main(String[] args) {
    MyDynamicThreadPool pool = new MyDynamicThreadPool(10, 5, 10);
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
        if (this.kxWorkers.size() == 0) {
          if (this.currThreadSize < this.maxThreadsSize) {
            new Worker(this, task).start();
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
    for (Thread t : this.kxWorkers) {
      if (t.getState().equals(Thread.State.BLOCKED)
          || t.getState().equals(Thread.State.WAITING)) {
        t.interrupt();
      }
    }
  }

  public synchronized void printPoolInfo() {
    System.out.println("*******************线程池[" + this + "当前状态:");
    System.out.println("当前池中线程数：" + this.currThreadSize);
    System.out.println("工作线程数：" + this.workingWorkers.size());
    System.out.println("空闲线程数：" + this.kxWorkers.size());
    System.out.println("等待执行任务数：" + this.taskQueue.size());
  }

  private class Worker extends Thread {

    private BlockingQueue<Runnable> taskQueue;

    private MyDynamicThreadPool pool;

    private boolean kx = true;

    private Runnable firstTask = null;

    private int doneTaskCount = 0;

    public Worker(MyDynamicThreadPool pool, Runnable firstTask) {
      super();
      this.taskQueue = pool.taskQueue;
      this.pool = pool;
      this.firstTask = firstTask;
      this.pool.kxWorkers.add(this);
    }

    public void run() {
      while (this.pool.working || taskQueue.size() > 0) {
        Runnable task = null;
        if (this.firstTask != null) {
          task = this.firstTask;
          this.firstTask = null;
        } else {
          task = taskQueue.poll();
        }

        if (task == null) {
          if (!this.kx) {
            synchronized (this.pool) {
              this.pool.workingWorkers.remove(this);
              this.pool.kxWorkers.add(this);
            }
            this.kx = true;
          }
          // 等待60秒
          try {
            task = this.taskQueue.poll(60, TimeUnit.SECONDS);
          } catch (InterruptedException e1) {
            e1.printStackTrace();
          }
          if (task == null) {
            // 判断线程是是否超过核心线程数，超过则让该空闲线程结束
            synchronized (this.pool) {
              if (this.pool.currThreadSize > this.pool.coreThreadsSize) {
                break;
              }
            }
            // 否则继续等待
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
            synchronized (this.pool) {
              this.pool.kxWorkers.remove(this);
              this.pool.workingWorkers.add(this);
            }
            this.kx = false;
          }
          try {
            task.run();
          } catch (Exception e) {
            e.printStackTrace();
          }

          this.doneTaskCount++;
          System.out.println(Thread.currentThread().getName()
              + "累计完成 " + this.doneTaskCount + "个任务");
        }

      }

      synchronized (this.pool) {
        if (this.kx) {
          pool.kxWorkers.remove(this);
        } else {
          pool.workingWorkers.remove(this);
        }
        this.pool.currThreadSize--;
        System.out.println(Thread.currentThread().getName()
            + "---结束。池中剩余" + this.pool.currThreadSize);
      }
    }
  }
}
