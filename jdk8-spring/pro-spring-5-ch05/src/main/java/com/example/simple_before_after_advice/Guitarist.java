package com.example.simple_before_after_advice;

import com.example.model.Singer;

public class Guitarist implements Singer {

  private String lyric = "You're gonna live forever in me";

  @Override
  public void sing() {
    System.out.println(lyric);
  }

}
