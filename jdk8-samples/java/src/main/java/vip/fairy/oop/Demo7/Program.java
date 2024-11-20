package vip.fairy.oop.Demo7;

public class Program {

  public static void main(String[] args) {
    TVControl tc = new TVControl("创维", 42);
    tc.powerOnorOff();

    tc.soundUp(100);
    tc.soundDown(200);
    tc.mute();
    tc.powerOnorOff();

  }
}
