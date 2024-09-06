package vip.fairy.sample_commond_3;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class App {

  public interface ServiceCommand {

    void save();

    void get();
  }

  class Service1Command implements ServiceCommand {

    @Override
    public void save() {
      System.out.println("service 1 save");
    }

    @Override
    public void get() {
      System.out.println("service 1 get");
    }

  }

  class Service2Command implements ServiceCommand {


    @Override
    public void save() {
      System.out.println("service 2 save");
    }

    @Override
    public void get() {
      System.out.println("service 2 get");
    }

  }

  class ServiceClient {

    public ServiceClient() {
    }

    private ServiceCommand serviceCommand;

    public void setServiceType(String serviceType) {
      if (Objects.isNull(serviceType)) {
        return;
      }
      if (serviceType.equals("service1")) {
        this.serviceCommand = new Service1Command();
      } else if (serviceType.equals("service2")) {
        this.serviceCommand = new Service2Command();
      }
    }


    public void save() {
      if (Objects.nonNull(serviceCommand)) {
        serviceCommand.save();
      }
    }

    public void get() {
      if (Objects.nonNull(serviceCommand)) {
        serviceCommand.get();
      }
    }

  }

  @Test
  void test() {
    ServiceClient serviceClient = new ServiceClient();
    serviceClient.setServiceType("service1");
    serviceClient.save();
    serviceClient.get();

    serviceClient.setServiceType("service2");
    serviceClient.save();
    serviceClient.get();
  }

}
