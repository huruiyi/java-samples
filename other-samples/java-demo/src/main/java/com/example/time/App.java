package com.example.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

public class App {

  public static void main(String[] args) {
    Demo03(args);
  }

  public static void Demo00(String[] args) {
    ZoneId defaultZoneId = ZoneId.systemDefault();
    Date date = new Date();
    Instant instant = date.toInstant();

    LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
    System.out.println(localDate);

    Set<String> zoneIds = ZoneId.getAvailableZoneIds();
    for (String zoneId : zoneIds) {
      System.out.print(zoneId);
      System.out.print("\t\t\t\t\t\t");

      LocalDateTime localDateTime = instant.atZone(ZoneId.of(zoneId)).toLocalDateTime();
      System.out.println(localDateTime);
    }
  }

  public static void Demo01(String[] args) {
    LocalDate today = LocalDate.now();
    System.out.println("今天的日期:" + today);
  }

  public static void Demo02(String[] args) {
    LocalDate today = LocalDate.now();
    int year = today.getYear();
    int month = today.getMonthValue();
    int day = today.getDayOfMonth();

    System.out.println("year:" + year);
    System.out.println("month:" + month);
    System.out.println("day:" + day);
  }

  public static void Demo03(String[] args) {
    LocalDate date = LocalDate.of(2018, 2, 6);
    System.out.println("自定义日期:" + date);
  }

  public static void Demo04(String[] args) {
    LocalDate date1 = LocalDate.now();
    LocalDate date2 = LocalDate.of(2018, 2, 5);

    if (date1.equals(date2)) {
      System.out.println("时间相等");
    } else {
      System.out.println("时间不等");
    }
  }

  public static void Demo05(String[] args) {
    LocalDate date1 = LocalDate.now();

    LocalDate date2 = LocalDate.of(2018, 2, 6);
    MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
    MonthDay currentMonthDay = MonthDay.from(date1);

    if (currentMonthDay.equals(birthday)) {
      System.out.println("是你的生日");
    } else {
      System.out.println("你的生日还没有到");
    }
  }

  public static void Demo06(String[] args) {
    LocalTime time = LocalTime.now();
    System.out.println("获取当前的时间,不含有日期:" + time);
  }

  public static void Demo07(String[] args) {
    LocalTime time = LocalTime.now();
    LocalTime newTime = time.plusHours(3);
    System.out.println("三个小时后的时间为:" + newTime);
  }

  public static void Demo08(String[] args) {
    LocalDate today = LocalDate.now();
    System.out.println("今天的日期为:" + today);
    LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
    System.out.println("一周后的日期为:" + nextWeek);
  }

  public static void Demo09(String[] args) {
    LocalDate today = LocalDate.now();

    LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
    System.out.println("一年前的日期 : " + previousYear);

    LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
    System.out.println("一年后的日期:" + nextYear);
  }

  public static void Demo10(String[] args) {
    // Returns the current time based on your system clock and set to UTC.
    Clock clock = Clock.systemUTC();
    System.out.println("Clock : " + clock.millis());

    // Returns time based on system clock zone
    Clock defaultClock = Clock.systemDefaultZone();
    System.out.println("Clock : " + defaultClock.millis());
  }

  public static void Demo11(String[] args) {
    LocalDate today = LocalDate.now();

    LocalDate tomorrow = LocalDate.of(2018, 2, 6);
    if (tomorrow.isAfter(today)) {
      System.out.println("之后的日期:" + tomorrow);
    }

    LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
    if (yesterday.isBefore(today)) {
      System.out.println("之前的日期:" + yesterday);
    }
  }

  public static void Demo12(String[] args) {
    // Date and time with timezone in Java 8
    ZoneId america = ZoneId.of("America/New_York");
    LocalDateTime localtDateAndTime = LocalDateTime.now();
    ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
    System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
  }

  public static void Demo13(String[] args) {
    YearMonth currentYearMonth = YearMonth.now();
    System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
    YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
    System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
  }

  public static void Demo14(String[] args) {
    LocalDate today = LocalDate.now();
    if (today.isLeapYear()) {
      System.out.println("This year is Leap year");
    } else {
      System.out.println("2018 is not a Leap year");
    }
  }

  public static void Demo15(String[] args) {
    LocalDate today = LocalDate.now();
    LocalDate java8Release = LocalDate.of(2018, 12, 14);

    Period periodToNextJavaRelease = Period.between(today, java8Release);
    System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());
  }

  public static void Demo16(String[] args) {
    Instant timestamp = Instant.now();
    System.out.println("What is value of this instant " + timestamp.toEpochMilli());
  }

  public static void Demo17(String[] args) {
    String dayAfterTommorrow = "20180205";
    LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
    System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);
  }

  public static void Demo18(String[] args) {
    LocalDateTime date = LocalDateTime.now();

    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    //日期转字符串
    String str = date.format(format1);

    System.out.println("日期转换为字符串:" + str);

    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    //字符串转日期
    LocalDate date2 = LocalDate.parse(str, format2);
    System.out.println("日期类型:" + date2);
  }
}
