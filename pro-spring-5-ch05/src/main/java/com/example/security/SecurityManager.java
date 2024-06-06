package com.example.security;

public class SecurityManager {

  private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

  public void login(String userName, String password) {
    threadLocal.set(new UserInfo(userName, password));
  }

  public void logout() {
    threadLocal.set(null);
  }

  public UserInfo getLoggedOnUser() {
    return threadLocal.get();
  }
}
