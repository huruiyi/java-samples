package vip.fairy.model;

public class Person {

  private Long id;
  private String name;
  private Integer age;
  private String address;

  public Person() {
  }

  public Person(Long id, String name, Integer age, String address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
