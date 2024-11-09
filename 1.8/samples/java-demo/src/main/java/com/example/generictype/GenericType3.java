package com.example.generictype;

import java.util.ArrayList;


public class GenericType3 {

  public static void main(String[] args) {
    String name = "Arek"; //raw type
    String name2 = "Wiola"; //raw type

    ArrayList<String> n = new ArrayList<>();

    n.add(name);
    n.add(name2);

    String firstName = n.get(0);
    System.out.println(firstName);

    Box<String> box = new Box<>();
    Box<Employee> box2 = new Box<>();
  }
}


