package vip.fairy.demo9;

import org.junit.Test;

public class App {

  @Test
  public void demo1() {
    UserDao userDao = new UserDaoImpl();
    UserDao proxy = new JdkProxy(userDao).CreateProxy();
    proxy.save();
    proxy.delete();
    proxy.update();
    proxy.find();
  }

}
