package com.example.regex_pointcuts;


import com.example.model.Singer;

public class Guitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("Just keep me where the light is");
  }

  public void sing2() {
    System.out.println("Oh gravity, stay the hell away from me");
  }

  public void rest() {
    System.out.println("zzz");
  }

}
