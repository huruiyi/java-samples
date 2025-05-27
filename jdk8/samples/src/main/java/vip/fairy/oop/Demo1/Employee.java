package vip.fairy.oop.Demo1;

import java.util.Date;

public class Employee extends Person {

  final Date dateOfEmployment;

  Employee() {
    this.dateOfEmployment = new Date();
  }

  Employee(Date date) {
    this.dateOfEmployment = date;
  }

  Date getDateOfEmployment() {
    return dateOfEmployment;
  }
}
