package vip.fairy.oop.Demo2;

public class Employee {

  String name;
  Address address; // aggregation
  Job job; // composition

  public Employee(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  @Override
  public String toString() {
    return this.name + ": " + address.country;
  }

}
