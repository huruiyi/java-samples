package com.example.demo.chapter_2.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class RESTErrorHandlingController {


    @GetMapping("/entity-two/{userId}")
    public ResponseEntity<String> getEntity(@PathVariable final String userId) {
        return ResponseEntity.ok(getByUser(userId));
    }

    private String getByUser(String userId) {
        return "你大爷的 ";
    }

}
