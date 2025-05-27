package vip.fairy.unfiled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import vip.fairy.model.Person;
import vip.fairy.model.User;

//HTMLDocument
//SimpleDateFormat

public class Test_Json {

  @Test
  public void ObjToJson() {
    //Date date = new Date();
    //System.out.println(date.getTime());
    //System.out.println(date.getYear() + 1900);
    //System.out.println(date.getMonth() + 1);
    //System.out.println(date.getDay());

    Calendar calendar = Calendar.getInstance();

    Person person = new Person(1, "小云儿", 26);
    String json = JSON.toJSON(person).toString();
    System.out.println(json);
  }

  public void JsonToObj() {
    String json = "{\"name\":\"小云儿\",\"id\":1,\"age\":26}";
    Person person = JSON.parseObject(json, Person.class);
    System.out.println(person);

    JSONObject jsonObject = JSON.parseObject(json);
    System.out.println(jsonObject.getClass().getName());
    System.out.println(jsonObject);
    System.out.println(jsonObject.getShort("id"));
    System.out.println(jsonObject.getString("name"));
    System.out.println(jsonObject.get("age"));

    Object obj = JSON.parse(json);
    System.out.println(obj.getClass().getName());
    System.out.println(obj);
    System.out.println(((JSONObject) obj).getShort("id"));
    System.out.println(((JSONObject) obj).getString("name"));
    System.out.println(((JSONObject) obj).get("age"));
  }

  public void ListToJson() {
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
    String json = JSON.toJSON(list).toString();
    System.out.println(json);
  }

  public void JsonToList() {
    String strJson = "[{\"name\":\"小明\",\"id\":2,\"age\":23},{\"name\":\"小红\",\"id\":4,\"age\":14},{\"name\":\"小雪\",\"id\":6,\"age\":43},{\"name\":\"小云\",\"id\":8,\"age\":12},{\"name\":\"小王\",\"id\":0,\"age\":23},{\"name\":\"小胡\",\"id\":1,\"age\":38},{\"name\":\"小周\",\"id\":3,\"age\":23},{\"name\":\"小青\",\"id\":5,\"age\":20},{\"name\":\"小马\",\"id\":7,\"age\":15}]";

    JSONArray jsonArray = (JSONArray) JSON.parse(strJson);
    Iterator iterator = jsonArray.iterator();
    System.out.println("1:*************************************************************");

    for (int i = 0; i < jsonArray.size(); i++) {
      System.out.format("[%d] = %s\n", i, jsonArray.get(i));
    }

    System.out.println("2:*************************************************************");

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }

    System.out.println("3:*************************************************************");

    List<Person> list1 = jsonArray.toJavaList(Person.class);
    for (Person person : list1) {
      System.out.println(person);
    }

    System.out.println("4:*************************************************************");

    List<JSONObject> list2 = JSON.toJavaObject(jsonArray, List.class);
    for (JSONObject person : list2) {
      System.out.format("%s-%s-%d\n", person.get("id"), person.get("name"), person.get("age"));
    }

    System.out.println("5:*************************************************************");
    List<Person> list3 = JSON.toJavaObject(jsonArray, List.class);
    System.out.println(list3.size());
    System.out.println(list3.get(0));
    System.out.println(list3.toString());

    //Object[] toArray()
    //<T> T[] toArray(T[] a)
  }

  public void JsonDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    Date date = null;
    try {
      date = sdf.parse("2018-10-06");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    User user = new User(1, "小王", date);
    JSONObject jsonObject = (JSONObject) JSON.toJSON(user);
    System.out.println(jsonObject);

    User user2 = JSON.toJavaObject(jsonObject, User.class);
    System.out.println(user2.getName() + " " + user2.getDate());
  }
}
