package vip.fairy;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

class Time {
    @Test
    void test1() {
        String pattern = "hh:mm:ss a";

        //1. LocalTime
        LocalTime now = LocalTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern(pattern)));

        //2. LocalDateTime
        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println(nowTime.format(DateTimeFormatter.ofPattern(pattern)));
    }

    @Test
    @DisplayName("Adding Hours, Minutes and Seconds")
    void test2() {
        LocalDateTime updatedTime;
        LocalDateTime now = LocalDateTime.now();

        updatedTime = now.plusHours(2);
        System.out.println(updatedTime);

        updatedTime = now.plusMinutes(20);
        System.out.println(updatedTime);

        updatedTime = now.plusSeconds(300);
        System.out.println(updatedTime);

        updatedTime = now.plus(Duration.ofMillis(8000));
        System.out.println(updatedTime);

        updatedTime = now.plus(20, ChronoUnit.HOURS);
        System.out.println(updatedTime);


        Instant updatedInstant;
        Instant currentInstant = Instant.parse("2022-06-24T05:12:35Z");
        updatedInstant = currentInstant.plus(2, ChronoUnit.HOURS);
        System.out.println(updatedInstant);
        updatedInstant = currentInstant.plus(30, ChronoUnit.MINUTES);
        System.out.println(updatedInstant);

        updatedInstant = currentInstant.plusSeconds(300);
        System.out.println(updatedInstant);

        updatedInstant = currentInstant.plusMillis(8000);
        System.out.println(updatedInstant);

        updatedInstant = currentInstant.plusNanos(60000);
        System.out.println(updatedInstant);
    }


    @Test
    @DisplayName("Subtracting Hours, Minutes and Seconds")
    void test3() {
        LocalDateTime updatedTime;
        LocalDateTime now = LocalDateTime.now();

        updatedTime = now.minusHours(2);
        updatedTime = now.minusMinutes(20);
        updatedTime = now.minusSeconds(300);
        updatedTime = now.minus(Duration.ofMillis(8000));
        updatedTime = now.minus(20, ChronoUnit.HOURS);


        Instant updatedInstant;
        Instant currentInstant = Instant.parse("2022-06-24T05:12:35Z");

        updatedInstant = currentInstant.minus(2, ChronoUnit.HOURS);
        updatedInstant = currentInstant.minus(30, ChronoUnit.MINUTES);
        updatedInstant = currentInstant.minusSeconds(300);
        updatedInstant = currentInstant.minusMillis(8000);
        updatedInstant = currentInstant.minusNanos(600000);
    }

    @Test
    void java7Date() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.HOUR, 2);
        cal.add(Calendar.MINUTE, -15);
        cal.add(Calendar.SECOND, 10);
    }

    @Test
    @DisplayName("Java 8 plus and minus methods")
    void java8Date() {

        //1. Add and substract 1 day from LocalDate
        LocalDate today = LocalDate.now();             //Today
        LocalDate tomorrow = today.plusDays(1);     //Plus 1 day
        LocalDate yesterday = today.minusDays(1);   //Minus 1 day
//2. Add and substract 1 month from LocalDateTime
        LocalDateTime now = LocalDateTime.now();     //Current Date and Time
        LocalDateTime sameDayNextMonth = now.plusMonths(1);       //2018-08-14
        LocalDateTime sameDayLastMonth = now.minusMonths(1);      //2018-06-14
//3. Add and substract 1 year from LocalDateTime
        LocalDateTime sameDayNextYear = now.plusYears(1);     //2019-07-14
        LocalDateTime sameDayLastYear = now.minusYears(1);    //2017-07-14
    }

    @Test
    void test4() {
        Date today = new Date();
        System.out.println(today);

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

// Adding time
        cal.add(Calendar.YEAR, 2);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DATE, 2);
        cal.add(Calendar.DAY_OF_MONTH, 2);

// Subtracting time
        cal.add(Calendar.YEAR, -3);
        cal.add(Calendar.MONTH, -3);
        cal.add(Calendar.DATE, -3);
        cal.add(Calendar.DAY_OF_MONTH, -3);

// convert calendar to date
        Date modifiedDate = cal.getTime();
        System.out.println(modifiedDate);
    }

    @Test
    void test5() {
        long millis = 54321000;

        Duration duration = Duration.ofMillis(millis);

        long h = duration.toHours();
        long m = duration.toMinutes() % 60;
        long s = duration.getSeconds() % 60;

        String timeInHms = String.format("%02d:%02d:%02d", h, m, s);

        System.out.println(timeInHms);    //15:05:21
    }

    @Test
    void test6() {
        long millis = 54321000;
        String timeInHms = DurationFormatUtils.formatDuration(millis, "HH:mm:ss", true);
        System.out.println(timeInHms);    //15:05:21
//Without padding
        timeInHms = DurationFormatUtils.formatDuration(millis, "HH:mm:ss", false);
        System.out.println(timeInHms);    //15:5:21
    }

    @Test
    void test7() {
        ZonedDateTime zdt = ZonedDateTime.now();
        String formattedZdt = zdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println(formattedZdt);
        formattedZdt = zdt.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(formattedZdt);
        formattedZdt = zdt.format(DateTimeFormatter.ISO_INSTANT);
        System.out.println(formattedZdt);


    }

    @Test
    void test8() {
        ZonedDateTime zdt = ZonedDateTime.now();
        ZonedDateTime zdt1 = ZonedDateTime.of(2018, 1, 1, 1, 1, 1, 1, ZoneId.of("Asia/Kolkata"));
        ZonedDateTime zdt2 = ZonedDateTime.of(2018, 1, 1, 1, 1, 1, 1, ZoneId.of("Europe/Paris"));
    }

    @Test
    void test9() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        ZonedDateTime zdt = ZonedDateTime.now();
        String formattedZdt = zdt.format(formatter);
        System.out.println(formattedZdt);
    }
}
