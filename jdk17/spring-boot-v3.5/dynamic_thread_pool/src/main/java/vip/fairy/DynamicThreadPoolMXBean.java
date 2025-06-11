package vip.fairy;


public interface DynamicThreadPoolMXBean {

  int getCorePoolSize();

  void setCorePoolSize(int corePoolSize);

  int getMaximumPoolSize();

  void setMaximumPoolSize(int maximumPoolSize);

  int getActiveCount();

  float getActiveRatio();
}
