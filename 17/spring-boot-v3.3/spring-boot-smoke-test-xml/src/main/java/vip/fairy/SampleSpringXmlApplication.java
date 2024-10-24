package vip.fairy;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vip.fairy.config.BeanConfig;
import vip.fairy.model.Boy;
import vip.fairy.model.Girl;
import vip.fairy.service.HelloWorldService;

@SpringBootApplication
public class SampleSpringXmlApplication implements CommandLineRunner {

  private static final String CONTEXT_XML = "classpath:/META-INF/application-context.xml";

  @Autowired
  private HelloWorldService helloWorldService;

  @Override
  public void run(String... args) {
    System.out.println(this.helloWorldService.getHelloMessage());
  }

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication();
    application.setSources(Collections.singleton(CONTEXT_XML));
    ConfigurableApplicationContext run = application.run(args);

    BeanConfig bean = run.getBean(BeanConfig.class);
    Boy boy1 = bean.getBoy();
    Boy boy2 = bean.getBoy();
    //proxyBeanMethods = true时, 相等.
    System.out.println("两次调用的对象是否相等：" + (boy1 == boy2));

    Girl girl = bean.getGirl();
    System.out.println("该女生有只有一个男朋友吗：" + (girl.getBoyFriend() == boy1));
  }

}
