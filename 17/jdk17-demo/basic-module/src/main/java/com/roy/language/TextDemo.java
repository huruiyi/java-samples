package com.roy.language;

import org.junit.Test;

public class TextDemo {

  @Test
  public void test1() {
    String query =
        """
            SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB` \s
            WHERE `CITY` = '%s' \
            ORDER BY `EMP_ID`, `LAST_NAME`;
            """;
    System.out.println("===== query start =====");
    System.out.println(String.format(query, "合肥"));
    System.out.println("===== query stop =====");
  }
}
