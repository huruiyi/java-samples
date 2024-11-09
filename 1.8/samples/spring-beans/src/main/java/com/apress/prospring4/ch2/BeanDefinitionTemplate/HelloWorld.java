package com.apress.prospring4.ch2.BeanDefinitionTemplate;

import java.util.List;

public class HelloWorld {

  private List<Holiday> holidays;
  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Holiday> getHolidays() {
    return holidays;
  }

  public void setHolidays(List<Holiday> holidays) {
    this.holidays = holidays;
  }

  public void hello() {
    for (Holiday holiday : holidays) {
      System.out.println(holiday.toString());
    }
    System.out.println("Hello! " + message);
  }
}
