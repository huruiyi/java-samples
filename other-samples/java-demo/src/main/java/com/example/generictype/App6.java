package com.example.generictype;

import org.junit.jupiter.api.Test;


public class App6 {

  @Test
  void Test() {
    ActionAfterClick a = new ActionAfterClick() {
      @Override
      public void action() {
        System.out.println("I'm action from anonymous class");
      }
    };

    Button p1 = new Button();
    p1.addAction(a);

    Button p2 = new Button();
    p2.addAction(new ActionAfterClick() {
      @Override
      public void action() {
        System.out.println("I'm action from anonymous class for button p2");
      }
    });
  }

  interface ActionAfterClick {

    void action();
  }

  public class Button {

    void addAction(ActionAfterClick a) {
      a.action();
    }
  }
}
