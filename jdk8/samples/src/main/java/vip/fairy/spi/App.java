package vip.fairy.spi;

import java.util.ServiceLoader;

public class App {

  public static void main(String[] args) {
    ServiceLoader<MyService> serviceLoader = ServiceLoader.load(MyService.class);
    for (MyService service : serviceLoader) {
      service.execute();
    }
  }
}
