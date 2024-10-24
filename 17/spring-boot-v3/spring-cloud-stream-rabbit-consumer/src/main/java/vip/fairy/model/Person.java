package vip.fairy.model;

import lombok.Data;

@Data
public class Person {

  String name;
  Long processedTimestamp;

  public Person(String name, Long processedTimestamp) {
    this.name = name;
    this.processedTimestamp = processedTimestamp;
  }
}
