import com.apress.prospring4.ch2.beans.AmericanImpl;
import com.apress.prospring4.ch2.beans.ChineseImpl;
import com.apress.prospring4.ch2.beans.Person;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestBeans {

  @Test
  public void Test1() {
    AmericanImpl a = new AmericanImpl();
    a.setName("传统Bob");
    a.setAge(8);
    a.Speak();
    ChineseImpl b = new ChineseImpl();
    b.setName("传统小明");
    b.setAge(8);
    b.Speak();
  }

  @Test
  public void Test2() {
    XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
    Person person = (Person) factory.getBean("chinese");
    person.Speak();
  }

  @Test
  public void Test3() {
    //容器从 CLASSPATH 中搜索 bean 配置文件。
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    //XML 文件的完整路径
    //ApplicationContext context = new FileSystemXmlApplicationContext("D:/iecas/SpringDemo/src/beans.xml");
    Person person = (Person) context.getBean("chinese");
    person.Speak();
    person = (Person) context.getBean("american");
    person.Speak();
  }
}
