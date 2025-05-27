package com.example.security;

public class UserInfo {

  private final String userName;
  private final String password;

  public UserInfo(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }
}
