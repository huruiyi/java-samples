package com.example.demo.salary.infrastructure.exceptions;

public class UserNotFoundException extends Throwable {
  public UserNotFoundException(String msg) {
    super(msg);
  }
}
