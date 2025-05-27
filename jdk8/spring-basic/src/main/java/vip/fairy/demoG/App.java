package vip.fairy.demoG;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.fairy.demoG.config.ShopConfiguration;


public class App {

  public static void main(String[] args) throws Exception {

    ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

    Product product1 = context.getBean("aaa", Product.class);
    System.out.println(product1);

    Product product2 = context.getBean("cdrw", Product.class);
    System.out.println(product2);
  }
}
