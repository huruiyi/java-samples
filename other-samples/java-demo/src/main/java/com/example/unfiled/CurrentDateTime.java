package com.example.unfiled;

import java.util.Calendar;

public class CurrentDateTime {

  static Calendar t;
  static int y, m, d, hh, mm, ss;

  static {
    t = Calendar.getInstance();
    y = t.get(Calendar.YEAR);
    m = t.get(Calendar.MONTH) + 1;
    d = t.get(Calendar.DATE);
    hh = t.get(Calendar.HOUR_OF_DAY);
    mm = t.get(Calendar.MINUTE);
    ss = t.get(Calendar.SECOND);
  }

  public static String getDate() {
    return y + "年" + m + "月" + d + "日";
  }

  public static String getTime() {
    return hh + "小时" + mm + "分" + ss + "秒";
  }
}
