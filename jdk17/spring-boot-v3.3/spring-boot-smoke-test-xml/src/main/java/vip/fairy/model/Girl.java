package vip.fairy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Girl {

  public String name;
  public int age;
  public Boy boyFriend;

  public Girl(String name, int age) {
    this.name = name;
    this.age = age;
  }

}
