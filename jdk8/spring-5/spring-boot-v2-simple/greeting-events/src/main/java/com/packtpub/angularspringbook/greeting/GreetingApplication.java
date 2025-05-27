package com.packtpub.angularspringbook.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingApplication.class, args);
    }

}


//@Component
//class AppInitializer implements ApplicationListener<ApplicationReadyEvent> {
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        System.out.println("AppInitializer:: on ApplicationReadyEvent");
//    }
//}


