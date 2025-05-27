package vip.fairy.test;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import vip.fairy.fun.Func;

public class FuncTest {

  Func func;

  @BeforeEach
  void initFunc() {
    func = new Func();
    System.out.println("open...");
  }

  @Test
  void addTest() {
    int a = 1;
    int b = 2;
    assertEquals(func.add(a, b), 3);
    System.out.println(func.add(a, b));
  }

  @Test
  void subTest() {
    int a = 2;
    int b = 1;
    assertEquals(func.sub(a, b), 1);
    System.out.println(func.sub(a, b));
  }

  @AfterEach
  void closeFun() {
    System.out.println("close...");
  }

  @Timeout(5)
  @RepeatedTest(3)
  void pollUntil() throws InterruptedException {
      Thread.sleep(4000); // custom poll interval
  }


}
