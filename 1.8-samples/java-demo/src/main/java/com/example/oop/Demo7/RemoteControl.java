package com.example.oop.Demo7;

public interface RemoteControl {

  int min_sound = 0;
  int max_sound = 100;

  boolean powerOnorOff();

  int soundUp(int increacement);

  int soundDown(int decreasement);

  void mute();

  int setChannel(int newChannel);

  int channelUp();

  int channelDown();
}
