package vip.fairy.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo {

  public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
    // Demo1();
    // Demo2_NewString();
    // Demo3_Constructor();
    // Demo4_Constructors();
    // Demo5_Interfaces();
    // Demo6_Superclass();
    // Demo7_Methods();

    Demo8_Fields();

    // Demo9_FiledsValue();
    // Demo10_SetClassValue();
    // Demo11_InvokeMethod();
    // Demo12_Array();
  }

  static void Demo1() {
    Test t = new Test();

    System.out.println(t.getClass());
    System.out.println(t.getClass().getName());

    // 方式一：
    Class<? extends Test> c1 = t.getClass();
    System.out.println(c1);

    // 方式二：
    // 为了避免特殊性，这里不用Test类，而用java库中的String类
    Class<String> c2 = String.class;
    System.out.println(c2);

    // 方式三：
    // forName()方法会抛出异常
    Class<?> c3 = null;
    try {
      c3 = Class.forName("com.example.reflection.Test");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(c3);
  }

  static void Demo2_NewString() {
    // 实例化Class对象，forName()方法会抛异常
    Class<?> c = null;
    try {
      // 这里需要完整的包名和类名
      c = Class.forName("java.lang.String");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // 生成一个字符串的引用
    String s = null;
    try {
      // 将构造好的对象向下转型为String类
      // newInstance()方法会抛异常
      s = (String) c.newInstance();
      s = "abcdefg";
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    System.out.println("字符串长度： " + s.length());
  }

  static void Demo3_Constructor() throws NoSuchMethodException, SecurityException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // 下面的几个方法抛出来的异常太多，为了代码的紧凑性，这里就直接抛给虚拟机了
    Class<?> c = null;
    try {
      c = Class.forName("java.lang.String");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    char[] ch = {'h', 'e', 'l', 'l', 'o'};
    String s = null;
    // 获得Class类对象的有参构造方法，括号里面参数的写法是：类型.class
    Constructor<?> con = c.getConstructor(char[].class);
    // 用此构造方法构造一个新的字符串对象，参数为一个char数组
    s = (String) con.newInstance(ch);
    System.out.println("构造的字符串：" + s);
  }

  static void Demo4_Constructors() {
    Class<?> c = null;
    try {
      c = Class.forName("java.lang.Boolean");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // 这里的getConstructors()方法返回的是一个Constructor数组
    Constructor<?>[] cons = c.getConstructors();
    // 打印的方式你可以自己写，为了方便我用Arrays.toString()，凑合着看
    System.out.println(Arrays.toString(cons));
  }

  static void Demo5_Interfaces() {
    Class<?> c = null;
    try {
      c = Class.forName("java.lang.Boolean");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    Class<?>[] in = c.getInterfaces();
    System.out.println(Arrays.toString(in));
  }

  static void Demo6_Superclass() {
    Class<?> c = null;
    try {
      c = Class.forName("java.lang.Boolean");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // 注意了，这里不会是数组，why？
    Class<?> su = c.getSuperclass();
    System.out.println(su);
  }

  static void Demo7_Methods() {
    Class<?> c = null;
    Employee employee = new Employee("小王");

    c = employee.getClass();
    // c = Class.forName("java.lang.Boolean");
    Method[] methods = c.getMethods();

    for (Method method : methods) {
      System.out.println(method.getName());
      if (method.getName().equals("toString")) {
        try {
          System.out.println(method.invoke(employee));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static void Demo8_Fields()
      throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

    Class<?> personClass = Class.forName("vip.fairy.reflect.Person");

    Field[] fields = personClass.getDeclaredFields();
    for (Field field : fields) {
      System.out.println(field);
    }
    Person person = (Person) personClass.newInstance();
    person.sayHi();

    Object object = personClass.getDeclaredConstructor().newInstance();
    personClass.getMethod("setName", String.class).invoke(object, "张三");
    personClass.getMethod("setAge", int.class).invoke(object, 12);
    personClass.getMethod("showInfo").invoke(object);

    personClass.getMethod("print", int.class).invoke(person, 123);
    personClass.getMethod("say", String.class).invoke(person, "hello");
    personClass.getMethod("sayHi").invoke(person);

    Object result = personClass.getMethod("add", int.class, int.class).invoke(person, 12, 13);
    System.out.println(result);
  }

  static void Demo10_SetClassValue()
      throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Employee p = new Employee("王二狗");
    System.out.println(p);
    Class<?> c = p.getClass();

    // 定义要修改的属性
    Field f = c.getDeclaredField("name");
    f.setAccessible(true);
    // 修改属性，传入要设置的对象和值
    f.set(p, "张二蛋");
    System.out.println(p);
  }

  static void Demo11_InvokeMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    Person p = new Person();
    Class<?> c = p.getClass();

    // getMethod()方法需要传入方法名，和参数类型
    Method m1 = c.getMethod("print", int.class);
    // invoke()表示调用的意思，需要传入对象和参数
    m1.invoke(p, 10);

    Method m2 = c.getMethod("say", String.class);
    // 这里的null表示不由对象调用，也就是静态方法
    m2.invoke(null, "你妹");
  }

  static void Demo12_Array() {
    int[] arr = {1, 2, 3, 4, 5};
    Class<?> c = arr.getClass().getComponentType();

    System.out.println("数组类型： " + c.getName());
    int len = Array.getLength(arr);
    System.out.println("数组长度： " + len);
    System.out.print("遍历数组： ");
    for (int i = 0; i < len; i++) {
      System.out.print(Array.get(arr, i) + " ");
    }
    System.out.println();
    // 修改数组
    System.out.println("修改前的第一个元素： " + Array.get(arr, 0));
    Array.set(arr, 0, 3);
    System.out.println("修改后的第一个元素： " + Array.get(arr, 0));
  }

  @org.junit.jupiter.api.Test
  void Demo9_FiledsValue()
      throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    Person p = new Person("zhangsan", 12);
    Class<?> c = p.getClass();

    // 获取公共属性的值
    Field f1 = c.getField("name");
    // get(p)表明要获取是哪个对象的值
    String str = (String) f1.get(p);
    System.out.println("姓名： " + str);

    // 获取私有属性的值
    Field f2 = c.getDeclaredField("age");
    // age是私有属性，所以要设置安全检查为true
    f2.setAccessible(true);
    int age = (int) f2.get(p);
    System.out.println("年龄： " + age);
  }
}
