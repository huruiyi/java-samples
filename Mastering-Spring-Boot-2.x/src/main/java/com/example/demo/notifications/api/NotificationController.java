package com.example.demo.notifications.api;

import com.example.demo.notifications.domain.HelloMessage;
import com.example.demo.notifications.domain.SalaryAddedNotification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/salary-add")
    @SendTo("/topic/greetings")
    public HelloMessage welcomeUser(SalaryAddedNotification salaryAddedNotification) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new HelloMessage("Hello, " + salaryAddedNotification.getName() + "!");
    }

}