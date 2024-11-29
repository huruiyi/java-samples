package vip.fairy.security;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class AlgorithmsTest {

  @Test
  void test1() {
    Provider[] providers = Security.getProviders();
    for (Provider provider : providers) {
      System.out.println(provider.getName() + " - " + provider.getInfo());
      Set<Service> services = provider.getServices();
      for (Service service : services) {
        //   Set<String> algorithms = Security.getAlgorithms(service.getAlgorithm());
        System.out.println("\t" + service.getAlgorithm());
      }
    }
  }

  @Test
  void test2() {
    Provider[] messageDigests = Security.getProviders("MessageDigest");
    for (Provider messageDigest : messageDigests) {
      System.out.println(messageDigest);
    }
  }

  @Test
  void test3() {
    // 获取所有支持 MessageDigest 服务的安全提供者
    Provider provider = Security.getProvider("SUN");
    System.out.println("MessageDigest Providers and their supported algorithms:");
    System.out.println("Provider: " + provider.getName() + " (" + provider.getInfo() + ")");
    for (Provider.Service service : provider.getServices()) {
      if ("MessageDigest".equals(service.getType())) {
        System.out.println(" - Algorithm: " + service.getAlgorithm());
      }
    }
  }

  @Test
  void test4() {
    Provider sunProvider = Security.getProvider("SUN");
    if (sunProvider != null) {
      for (Provider.Service service : sunProvider.getServices()) {
        if ("MessageDigest".equals(service.getType())) {
          System.out.println("Found MessageDigest: " + service.getAlgorithm());
        }
      }
    } else {
      System.out.println("SUN provider not found.");
    }
  }


}
