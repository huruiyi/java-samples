package vip.fairy.model;


import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class User {

  private Integer id;

  private String name;

  @JSONField(format = "yyyy-mm-dd")
  private Date date;


  public User(Integer id, String name, Date date) {
    this.id = id;
    this.name = name;
    this.date = date;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", date=" + date + '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
