package com.example.demo.chapter_7;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.demo.eventbus.api.EventBus;
import com.example.demo.eventbus.domain.Event;
import com.example.demo.salary.details.api.SalaryDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.metrics.instrument.stats.quantile.GKQuantiles;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Component
public class SalaryDetailsWithFallback implements SalaryDetails {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private RestTemplate rest;
    private EventBus eventBus;

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    public SalaryDetailsWithFallback(@Qualifier("salaries-client") RestTemplate rest, EventBus eventBus) {
        this.rest = rest;
        this.eventBus = eventBus;
    }

    @Override
    @HystrixCommand(fallbackMethod = "reliable")
    public String getInfoAboutSalary(String account) {
        log.warn("some request for account " + account);
        return request(account);
    }

    public String request(String account) {
        Timer timer = meterRegistry.timerBuilder("salary_detail_request").quantiles(GKQuantiles.quantiles(0.99, 0.90, 0.50).create()).create();
        return timer.record(() -> requestForSalaryDetails(account));
    }

    private String requestForSalaryDetails(String account) {
        return "{\"name\" : " + account + ", \"time\": " + Instant.now() + "}";
    }

    public String reliable(String account) {
        meterRegistry.counter("hystrix_fallback").increment();
        return "Info about salary: " + account + " is not currently not available";
    }

    @Scheduled(fixedRate = 1000)
    public void processEvents() {
        Event event = eventBus.receive();
        if (event != null) {
            log.info("received event to process :" + event);
        }

    }
}
