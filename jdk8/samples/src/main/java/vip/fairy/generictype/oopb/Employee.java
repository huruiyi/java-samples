package vip.fairy.generictype.oopb;


class Employee implements java.lang.Comparable {

  private double salary;

  public Employee(double salary) {
    this.salary = salary;
  }

  double getSalary() {
    return this.salary;
  }

  @Override
  public int compareTo(Object t) {
    Employee tmp = (Employee) t;

    if (this.salary < tmp.salary) {
      return -1;
    }

    if (this.salary > tmp.salary) {
      return 1;
    }

    return 0;
  }
}
