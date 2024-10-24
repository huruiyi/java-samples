package vip.fairy;

import org.junit.jupiter.api.Test;

public class Reg {

    @Test
    void test1() {
        String input = "1234567890";
        String number = input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");   //(123) 456-7890
        System.out.println(number);
    }

    @Test
    void test2() {
        String input = "1234567890";
        String number = input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");   //123-456-7890
        System.out.println(number);
    }
}
