package com.example.property_placeholder;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PlaceHolderDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/property-placeholder-context.xml");
        ctx.refresh();

        AppProperty appProperty = ctx.getBean("appProperty", AppProperty.class);

        System.out.println("application.home: " + appProperty.getApplicationHome());
        System.out.println("user.home: " + appProperty.getUserHome());

        ctx.close();
    }
}
