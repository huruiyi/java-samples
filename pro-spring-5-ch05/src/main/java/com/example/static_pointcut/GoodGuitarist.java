package com.example.static_pointcut;


import com.example.model.Singer;

public class GoodGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("Who says I can't be free From all of the things that I used to be");
  }

}
