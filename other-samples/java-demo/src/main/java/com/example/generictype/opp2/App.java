package com.example.generictype.opp2;


import org.junit.jupiter.api.Test;

public class App {

  @Test
  void Test() {

    ActionAfterClick x0 = () -> {
      System.out.println("I'm action from anonymous class");
    };

    ActionAfterClick x1 = () -> System.out.println("I'm action from anonymous class");

    ActionAfterClick2 x2 = (a, b) -> a + b;

    Button p = new Button();
    p.addAction(x0);

    Button p2 = new Button();
    p2.addAction(new ActionAfterClick() {
      @Override
      public void action() {
        System.out.println("I'm action from anonymous class for button p2");
      }
    });

    int result = p.addAction2(10, 15, x2);

    System.out.println(result);
  }

  interface ActionAfterClick2 {

    int action2(int a, int b);
  }

  public class Button {

    void addAction(ActionAfterClick a) {
      a.action();
    }

    int addAction2(int x, int y, ActionAfterClick2 a) {
      return a.action2(x, y);
    }
  }
}
