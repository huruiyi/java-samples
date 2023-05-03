package com.example.demo.chapter_2.api;

import com.example.demo.chapter_2.domain.Salary;
import com.example.demo.chapter_2.domain.SalaryDto;
import com.example.demo.chapter_2.persistance.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.metrics.instrument.DistributionSummary;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.metrics.instrument.simple.SimpleMeterRegistry;
import org.springframework.metrics.instrument.stats.hist.NormalHistogram;
import org.springframework.metrics.instrument.stats.quantile.GKQuantiles;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class SalaryController {

    private final SalaryRepository salaryRepository;
    private final DistributionSummary distributionSummary;
    private final Timer postTimer;

    @Autowired
    public SalaryController(SalaryRepository salaryRepository) {

        MeterRegistry meterRegistry = new SimpleMeterRegistry();

        this.salaryRepository = salaryRepository;
        postTimer = meterRegistry.timerBuilder("salary_create").quantiles(GKQuantiles.quantiles(0.99, 0.90, 0.80, 0.50).create()).create();

        distributionSummary = meterRegistry.summaryBuilder("user_id_length").histogram(new NormalHistogram<>(NormalHistogram.linear(0, 5, 10))).create();
    }

    @GetMapping("/salary/{userId}")
    public List<SalaryDto> getsalaries(@PathVariable final String userId) {
        distributionSummary.record(userId.length());
        return salaryRepository.findByUserId(userId).stream()
                .map(p -> new SalaryDto(p.getUserId(),
                        p.getAccountFrom(), p.getAccountTo(), p.getAmount()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/salary", consumes = APPLICATION_JSON_VALUE)
    public SalaryDto addSalary(@RequestBody SalaryDto salary) {
        return postTimer.record(
                () -> {
                    salaryRepository.save(
                            new Salary(salary.getUserId(),
                                    salary.getAccountFrom(),
                                    salary.getAccountTo(),
                                    salary.getAmount()));
                    return salary;
                }
        );
    }

}
