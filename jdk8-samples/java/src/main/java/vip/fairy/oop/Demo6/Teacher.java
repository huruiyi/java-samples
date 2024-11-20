package vip.fairy.oop.Demo6;

public class Teacher extends Person {

  // 扩充父类属性
  private String department;

  // 测试
  public static void main(String[] args) {
    Teacher t = new Teacher();
    t.setName("Alex");
    System.out.println(t.showName());
  }

  public String getDepartment() {
    return department;
  }

  // 扩充父类方法
  public void setDepartment(String theDept) {
    department = theDept;
  }

  // 方法覆盖
  public String showName() {
    return name + "老师";
  }
}
