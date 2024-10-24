package com.example.simple_name_matching;


import com.example.model.Guitar;
import com.example.model.Singer;

public class GrammyGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("sing: Gravity is working against me And gravity wants to bring me down");
  }

  public void sing(Guitar guitar) {
    System.out.println("play: " + guitar.play());
  }

  public void rest() {
    System.out.println("zzz");
  }

  public void talk() {
    System.out.println("talk");
  }
}
