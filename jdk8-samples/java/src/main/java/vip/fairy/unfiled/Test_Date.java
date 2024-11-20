package vip.fairy.unfiled;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_Date {

  @Test
  public void Test1() {
    for (ChronoUnit unit : ChronoUnit.values()) {
      System.out.printf("%10s \t %b \t\t %b \t\t %s %n", unit, unit.isDateBased(), unit.isTimeBased(), unit.getDuration());
    }
  }

  @Test
  public void Test2() {
    String[] dateTimeFormats = {
        "dd-MM-yyyy", /* d is day (in month), M is month, y is year */
        "d '('E')' MMM, YYYY", /*E is name of the day (in week), Y is year*/
        "w'th week of' YYYY", /* w is the week of the year */
        "EEEE, dd'th' MMMM, YYYY" /*E is day name in the week */
    };
    LocalDateTime now = LocalDateTime.now();
    for (String dateTimeFormat : dateTimeFormats) {
      System.out.printf("Pattern \"%25s\" \t\t %s\n", dateTimeFormat, DateTimeFormatter.ofPattern(dateTimeFormat).format(now));
    }
  }

  @Test
  public void Test3() {
    String[] timeFormats = {
        "h:mm",         /* h is hour in am/pm (1-12), m is minute */
        "H:mm",         /* H is hour in am/pm (1-23), m is minute */
        "hh 'o''clock'", /* '' is the escape sequence to print a single quote */
        "H:mm a",       /* H is hour in day (0-23), a is am/pm*/
        "hh:mm:ss:SS",  /* s is seconds, S is milliseconds */
        "K:mm:ss a"     /* K is hour in am/pm(0-11) */
    };
    LocalTime now = LocalTime.now();
    for (String timeFormat : timeFormats) {
      System.out.printf("Time in pattern \"%15s\" is %s %n", timeFormat, DateTimeFormatter.ofPattern(timeFormat).format(now));
    }
  }

  @Test
  @DisplayName("获取所有时区")
  public void Test4() {
    Set<String> zoneIds = ZoneId.getAvailableZoneIds();
    for (String zoneId : zoneIds) {
      System.out.println(zoneId);
    }
  }

  @Test
  @DisplayName("时区时间差")
  public void Test5() {
    ZoneId singaporeZone = ZoneId.of("Asia/Shanghai");
    ZonedDateTime dateTimeInSingapore = ZonedDateTime.of(LocalDateTime.of(2019, Month.MAY, 26, 12, 54), singaporeZone);

    ZoneId aucklandZone = ZoneId.of("America/New_York");
    ZonedDateTime sameDateTimeInAuckland = dateTimeInSingapore.withZoneSameInstant(aucklandZone);

    Duration timeDifference = Duration.between(dateTimeInSingapore.toLocalTime(), sameDateTimeInAuckland.toLocalTime());

    System.out.printf("Time difference between %s and %s zones is %d hours", singaporeZone, aucklandZone, timeDifference.toHours());
  }

  @Test
  @DisplayName("时间加法运算")
  public void Test6() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");

    ZonedDateTime departure = ZonedDateTime.of(
        LocalDateTime.of(2019, Month.DECEMBER, 13, 6, 15), ZoneId.of("Asia/Shanghai"));

    System.out.println("出发时间: " + dateTimeFormatter.format(departure));

    ZonedDateTime arrival = departure.withZoneSameInstant(ZoneId.of("Asia/Shanghai")).plusHours(10);

    System.out.println("到达时间: " + dateTimeFormatter.format(arrival));
  }

  @Test
  public void Test7() {
    // prints the current timestamp with UTC as time zone
    Instant currTimeStamp = Instant.now();
    System.out.println("Instant timestamp is: " + currTimeStamp);

    // prints the number of seconds as Unix timestamp from epoch time
    System.out.println("Number of seconds elapsed: " + currTimeStamp.getEpochSecond());

    // prints the Unix timestamp in milliseconds
    System.out.println("Number of milliseconds elapsed: " + currTimeStamp.toEpochMilli());
  }

  @Test
  public void DateDemo_00() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Date start = calendar.getTime();

    calendar.add(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.SECOND, -1);
    Date end = calendar.getTime();

    System.out.println("start======" + start);
    System.out.println("end========" + end);
  }


  @Test
  public void DateDemo_01() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    System.out.println(calendar.getTime());

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = simpleDateFormat.format(calendar.getTime());
    System.out.println(dateStr);

    Date date = simpleDateFormat.parse(dateStr);

    System.out.println(date);
  }

  @Test
  public void DateDemo_02() {
    Date objDate = new Date();
    String strDate;
    DateFormat simpleFormat;
    simpleFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
    strDate = objDate.toString();
    System.out.println("\n**********************");
    System.out.println("当前日期和时间");
    System.out.println("**********************\n");
    System.out.println("当前日期： " + strDate);
    System.out.println("\n格式化后： " + simpleFormat.format(objDate));
  }

  @Test
  public void DateDemo_03() {

    Date today = new Date();
    System.out.println("字符串格式：" + today.toString());

    DateFormat f0 = DateFormat.getInstance();
    System.out.println("系统  格式：" + f0.format(today));

    DateFormat f1 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
    DateFormat f2 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
    String s1 = f1.format(today);
    String s2 = f2.format(today);
    System.out.println("中国  格式" + s1);
    System.out.println("中国  格式" + s2);
  }

  @Test
  public void DateDemo_04() {
    System.out.println(CurrentDateTime.getDate());
    System.out.println(CurrentDateTime.getTime());

    Calendar today = Calendar.getInstance();
    today.add(Calendar.DAY_OF_WEEK, 7);
    System.out.println(today.get(Calendar.YEAR) + "年" + (today.get(Calendar.MONTH) + 1) + "月" + today.get(Calendar.DAY_OF_MONTH) + "日");
  }
}
