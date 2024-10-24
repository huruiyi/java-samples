package com.example.oop.Demo4;

public class App {

  public static void main(String[] args) {
    Demo2();
  }


  static void Demo1() {
    // MONSTERS - ZOMBIES - SKELETONS - hit points, attack, speed

    Monster monster = new Monster(100, 10);

    monster.attack();

    System.out.println(monster.hitPoints);
    System.out.println(monster.speed);
  }

  static void Demo2() {
    Skeleton skeleton = new Skeleton(1000, 500);
    Skeleton skeleton2 = new Skeleton(1000, 500, "Bow");
    System.out.println(skeleton.hitPoints);
    skeleton.attack();
  }
}
