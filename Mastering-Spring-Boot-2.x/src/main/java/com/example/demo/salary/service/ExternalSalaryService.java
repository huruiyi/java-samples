package com.example.demo.salary.service;

import com.example.demo.chapter_2.domain.Salary;
import com.example.demo.chapter_2.persistance.SalaryRepository;
import com.example.demo.eventbus.api.EventBus;
import com.example.demo.eventbus.domain.Event;
import com.example.demo.salary.api.SalaryService;
import com.example.demo.salary.infrastructure.configuration.SalaryServiceSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ExternalSalaryService implements SalaryService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MeterRegistry meterRegistry;

    private final SalaryServiceSettings salaryServiceSettings;
    private final SalaryRepository salaryRepository;
    private EventBus eventBus;

    @Autowired
    public ExternalSalaryService(SalaryServiceSettings salaryServiceSettings,
                                  SalaryRepository salaryRepository,
                                  EventBus eventBus) {
        this.salaryServiceSettings = salaryServiceSettings;
        this.salaryRepository = salaryRepository;
        this.eventBus = eventBus;
    }

    @Override
    public boolean pay(Salary salary) {
        if (salaryServiceSettings.getSupportedAccounts().contains(salary.getAccountTo())) {
            salaryRepository.save(salary);
            eventBus.publish(new Event("SAVED", "Saved salary " + salary));
            return true;
        }
        eventBus.publish(new Event("REJECTED", "Rejected salary " + salary));
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
