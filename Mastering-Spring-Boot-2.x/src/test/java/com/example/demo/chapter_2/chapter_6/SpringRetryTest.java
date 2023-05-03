package com.example.demo.chapter_2.chapter_6;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("integration")
public class SpringRetryTest {

    @Autowired
    private RetryTemplate retryTemplate;

    @Test
    public void givenRetryTemplate_whenRetryOnce_thenShouldSucceedOnRetry() throws Throwable {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        String result = retryTemplate.execute((RetryCallback<String, Throwable>) retryContext -> {
            System.out.println("run number: " + atomicInteger.get());
            if (atomicInteger.getAndIncrement() == 0) {
                throw new RuntimeException("error while first query");
            } else {
                return "success";
            }
        });
        assertThat(result).isEqualTo("success");
    }

    @Test
    public void givenRetryTemplate_whenRetryOnceAndFailure_thenShouldPropagateError() throws Throwable {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        String result = retryTemplate.execute((RetryCallback<String, Throwable>) retryContext -> {
            System.out.println("run number: " + atomicInteger.getAndIncrement());
            throw new RuntimeException("error while query");
        });
        System.out.println(result);
    }
}