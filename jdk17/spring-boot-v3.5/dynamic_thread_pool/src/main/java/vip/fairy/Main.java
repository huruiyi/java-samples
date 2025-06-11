package vip.fairy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws Exception {
    DynamicThreadPool dynamicThreadPool = new DynamicThreadPool(2, 4, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
    DynamicThreadPoolMBean mBean = new DynamicThreadPoolMBean(dynamicThreadPool);

    while (true) {
      System.out.println("CorePoolSize:" + dynamicThreadPool.getThreadPoolExecutor().getCorePoolSize());
      System.out.println("MaximumPoolSize:" + dynamicThreadPool.getThreadPoolExecutor().getMaximumPoolSize());
      try {
        for (int i = 0; i < 4; i++) {
          dynamicThreadPool.getThreadPoolExecutor().execute(() -> {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          });
        }
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
