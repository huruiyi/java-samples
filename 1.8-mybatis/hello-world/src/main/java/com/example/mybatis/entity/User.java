package com.example.mybatis.entity;

/**
 * Created by lzc
 * 2020/1/1 14:30
 */
public class User {
    private Integer id;
    private String userName;
    private String userEmail;
    private String userCity;
    private Integer age;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserCity() {
        return userCity;
    }
    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userCity='" + userCity + '\'' +
            ", age=" + age +
            '}';
    }
}
