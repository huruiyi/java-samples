package com.example.demo.chapter_2.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PropagatesExceptionEndpoint {

    @GetMapping("/entity/{userId}")
    public String getEntity(@PathVariable final String userId) {
        return getByUser(userId);

    }

    private String getByUser(String userId) {
        return "user:PropagatesExceptionEndpoint";
    }
}
