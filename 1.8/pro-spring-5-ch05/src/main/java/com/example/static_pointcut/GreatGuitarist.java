package com.example.static_pointcut;


import com.example.model.Singer;

public class GreatGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("I shot the sheriff, But I did not shoot the deputy");
  }

}
