package com.example.collection;

import com.alibaba.fastjson.JSONArray;
import com.example.model.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class App {

  @Test
  public void AddALL() {
    List<Person> list1 = new ArrayList<>();
    List<Person> list2 = new ArrayList<>();
    List<Person> list3 = new ArrayList<>();
    List<Person> list4 = new ArrayList<>();

    list1.add(new Person(2, "小明", 23));
    list1.add(new Person(4, "小红", 14));
    list1.add(new Person(6, "小雪", 43));

    list2.add(new Person(8, "小云", 12));
    list2.add(new Person(0, "小王", 23));
    list2.add(new Person(1, "小胡", 38));

    list3.add(new Person(3, "小周", 23));
    list3.add(new Person(5, "小青", 20));
    list3.add(new Person(7, "小马", 15));

    list4.add((new Person(71, "小马", 15)));
    List<Person> list = new ArrayList<>();

    list.addAll(list1);
    list.addAll(list2);
    list.addAll(list3);
    list.addAll(list4);

    System.out.println(list.size());
  }

  @Test
  public void UUIDDemo() {
    System.out.println(UUID.randomUUID());
  }

  @Test
  public void TreeSetStringDemo() {
    //实现了 Comparable<String> 接口 ,自行排序
    TreeSet<String> treeSetStr = new TreeSet<String>();
    treeSetStr.add("abc");
    treeSetStr.add("xyz");
    treeSetStr.add("opq");

    for (String string : treeSetStr) {
      System.out.println(string);
    }
  }

  @Test
  public void TreeSetObjectDemo() {
    TreeSet<Person> tsPersons = new TreeSet<Person>();
    tsPersons.add(new Person(2, "小明", 23));
    tsPersons.add(new Person(4, "小红", 14));
    tsPersons.add(new Person(6, "小雪", 43));
    tsPersons.add(new Person(8, "小云", 12));
    tsPersons.add(new Person(0, "小王", 23));
    tsPersons.add(new Person(1, "小胡", 38));
    tsPersons.add(new Person(3, "小周", 23));
    tsPersons.add(new Person(5, "小青", 20));
    tsPersons.add(new Person(7, "小马", 15));

    System.out.println("1:***********************************************************");

    Object[] persons = tsPersons.toArray();
    for (Object person : persons) {
      System.out.println(person.toString());
    }

    System.out.println("2:***********************************************************");

    for (int i = 0; i < persons.length; i++) {
      Person person = (Person) persons[i];
      System.out.println(person.getName());
    }
    System.out.println("3:***********************************************************");

    System.out.println(tsPersons.first());
    System.out.println(tsPersons.last());

    System.out.println("4:***********************************************************");

    Iterator<Person> ipersons = tsPersons.iterator();
    for (Iterator<Person> iterator = ipersons; iterator.hasNext(); ) {
      Person person = iterator.next();
      System.out.println(person.toString());
    }
    System.out.println("5:***********************************************************");
    Iterator iteratorPersons = tsPersons.iterator();
    while (iteratorPersons.hasNext()) {
      System.out.println(iteratorPersons.next());
    }

  }

  @Test
  public void JsonDemo() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.add(new Person(2, "小明", 23));
    jsonArray.add(new Person(4, "小红", 14));
    jsonArray.add(new Person(6, "小雪", 43));
    jsonArray.add(new Person(8, "小云", 12));
    jsonArray.add(new Person(0, "小王", 23));
    jsonArray.add(new Person(1, "小胡", 38));
    jsonArray.add(new Person(3, "小周", 23));
    jsonArray.add(new Person(5, "小青", 20));
    jsonArray.add(new Person(7, "小马", 15));
  }

  @Test
  public void StringTokenizerDemo() {
    StringTokenizer stringTokenizer = new StringTokenizer("Hello World,世界你好");
    System.out.println(stringTokenizer.hasMoreElements());
    System.out.println(stringTokenizer.hasMoreTokens());
  }

  @Test
  public void SetToIntDemo() {
    Set<Integer> allSet = new TreeSet<Integer>();
    allSet.add(3);
    allSet.add(2);
    allSet.add(1);
    allSet.add(4);
    Object[] num1 = allSet.toArray();
    int[] num = SetToInt(allSet);
    for (int j = 0; j < num.length; j++) {
      System.out.print(num[j] + " ");
    }
  }

  public int[] SetToInt(Set<Integer> allSet) {
    Integer[] temp = allSet.toArray(new Integer[]{});
    int[] intArray = new int[temp.length];
    for (int i = 0; i < temp.length; i++) {
      intArray[i] = temp[i].intValue();
    }
    return intArray;
  }

  @Test
  public void SetToInt2() {
    Set<Integer> allSet = new TreeSet<Integer>();
    allSet.add(3);
    allSet.add(2);
    allSet.add(1);
    allSet.add(4);
    Object[] obj = allSet.toArray();
    int temp[] = new int[obj.length];
    for (int i = 0; i < obj.length; i++) {
      temp[i] = (Integer) obj[i];
      System.out.print(temp[i] + " ");
    }
  }

  @Test
  public void SortDemo() {
    List<Person> list = new ArrayList<Person>();
    list.add(new Person(2, "小明", 23));
    list.add(new Person(4, "小红", 14));
    list.add(new Person(6, "小雪", 43));
    list.add(new Person(8, "小云", 12));
    list.add(new Person(0, "小王", 23));
    list.add(new Person(1, "小胡", 38));
    list.add(new Person(3, "小周", 23));
    list.add(new Person(5, "小青", 20));
    list.add(new Person(7, "小马", 15));

    list.sort(new Comparator<Person>() {
      public int compare(Person o1, Person o2) {
        return o1.getAge().compareTo(o2.getAge());
      }
    });
    for (Person person : list) {
      System.out.println(person);
    }
  }
}

