package com.example.unfiled;

import com.example.enums.BaseDataEnum;
import com.example.enums.StatusEnum;

public class EnumDemos {

  public static void main(String[] args) {
    String text = BaseDataEnum.BM_CITY.getText();
    System.out.println(text);

    System.out.println(BaseDataEnum.BM_CITY.getValue());
    System.out.println(BaseDataEnum.BM_COUNTRY.toString());

    BaseDataEnum cEnum = BaseDataEnum.valueOf("BM_COUNTRY");
    System.out.println(cEnum.getText());
    System.out.println(cEnum.getValue());
  }


  public static void Demo1() {
    System.out.println(StatusEnum.Draf);
    System.out.println(StatusEnum.Draf.toString());

    String status = StatusEnum.Draf.getStatus();
    System.out.println(status);

    StatusEnum draf = StatusEnum.valueOf("Draf");
    System.out.println(draf.status);

    for (StatusEnum value : StatusEnum.values()) {
      System.out.println(value.toString() + " " + value.getStatus());
    }

    System.out.println(StatusEnum.valueOf("Draf").status);
  }
}
