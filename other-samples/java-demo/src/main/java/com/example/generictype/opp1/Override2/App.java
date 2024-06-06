package com.example.generictype.opp1.Override2;

public class App {

  public static void main(String[] args) {
    Person[] people = new Person[400]; // null, null, null, null
    people[0] = new Employee("Arkadiusz", "Włodarczyk", 100000000);
    people[1] = new Student("asfasf", "fasf");

    for (int i = 0; i < people.length; i++) {
      if (people[i] instanceof Student) {
        people[i].getDescription();
      }
    }

  }
}
