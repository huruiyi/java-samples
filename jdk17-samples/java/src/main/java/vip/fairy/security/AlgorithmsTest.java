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
      System.out.println(provider.getName());
      Set<Service> services = provider.getServices();
      for (Service service : services) {
     //   Set<String> algorithms = Security.getAlgorithms(service.getAlgorithm());
        System.out.println("\t"+service.getAlgorithm());
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

}
