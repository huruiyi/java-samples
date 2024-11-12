package com.example.generictype;

import org.junit.jupiter.api.Test;

public class App1 {


  @Test
  void Test() {
    Monitor monitor1 = new Monitor();
    monitor1.width = 1000;
    monitor1.height = 200;
    System.out.println(monitor1.width + "  " + monitor1.height);

    Monitor monitor2 = new Monitor();

    monitor2.width = 20000;
    monitor2.getWidth();

    monitor1.getWidth();

    String name = "Arkadiusz";

    System.out.println(name.charAt(4));
  }

  class Monitor {

    int width;
    int height;
    int brightness;

    void changeBrigthness() {

    }

    void getWidth() {
      System.out.println(width);
    }
  }


}
