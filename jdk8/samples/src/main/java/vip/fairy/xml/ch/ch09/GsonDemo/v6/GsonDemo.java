package vip.fairy.xml.ch.ch09.GsonDemo.v6;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class GsonDemo {

  public static void main(String[] args) throws IOException {
    Student s = new Student("John Doe", 820787, 89, 78,
        97, 65);
    Gson gson = new Gson();
    FileWriter fw = new FileWriter("student.json");
    gson.toJson(s, fw);
    fw.close();
  }

  static class Student {

    String name;
    int id;
    int[] grades;

    Student(String name, int id, int... grades) {
      this.name = name;
      this.id = id;
      this.grades = grades;
    }
  }
}
