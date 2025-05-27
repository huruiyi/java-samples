package vip.fairy.oop.Demo7;

public class TVControl implements RemoteControl {

  private String speace = null;
  private int screensize = 0;
  private boolean power_state = false;
  private int sounds = min_sound;
  private int min_channel = 0;
  private int max_channel = 100;

  public TVControl(String speace, int screensize) {
    this.speace = speace;
    this.screensize = screensize;
  }

  public int channelDown() {
    if (min_channel < 0) {

    }
    return 0;
  }

  public int channelUp() {
    if (max_channel > 100) {

    }
    return 0;
  }

  public void mute() {
    if (power_state == false) {
      return;
    }
    sounds = min_sound;
    System.out.println(screensize + "英寸的" + speace + "牌彩电" + "声音为：" + sounds);

  }

  public boolean powerOnorOff() {
    power_state = !power_state;
    System.out.println(screensize + "英寸的" + speace + "牌彩电" + (power_state ? "打开了" : "关闭了"));
    return power_state;
  }

  public int setChannel(int newChannel) {
    return 0;
  }

  public int soundDown(int decreasement) {
    if (power_state == false) {
      return 0;
    }
    sounds -= decreasement;
    sounds = Math.max(sounds, min_sound);
    System.out.println(screensize + "英寸的" + speace + "牌彩电" + "声音减少到" + sounds);
    return sounds;
  }

  public int soundUp(int increacement) {
    if (power_state == false) {
      return 0;
    }
    sounds += increacement;
    sounds = Math.min(sounds, max_sound);
    System.out.println(screensize + "英寸的" + speace + "牌彩电" + "声音增加到" + sounds);
    return sounds;
  }
}
