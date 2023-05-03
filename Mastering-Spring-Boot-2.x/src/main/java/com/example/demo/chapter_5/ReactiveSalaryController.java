package com.example.demo.chapter_5;

import com.example.demo.chapter_2.domain.SalaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.metrics.instrument.DistributionSummary;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.metrics.instrument.stats.hist.NormalHistogram;
import org.springframework.metrics.instrument.stats.quantile.GKQuantiles;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController()
public class ReactiveSalaryController {

  private final ReactiveSalaryService salaryRepository;
  private final DistributionSummary distributionSummary;
  private final Timer postTimer;

  @Autowired
  public ReactiveSalaryController(ReactiveSalaryService salaryRepository, MeterRegistry meterRegistry) {
    this.salaryRepository = salaryRepository;
    postTimer = meterRegistry
        .timerBuilder("salary_create")
        .quantiles(GKQuantiles.quantiles(0.99, 0.90, 0.80, 0.50).create()).create();

    distributionSummary = meterRegistry.summaryBuilder("user_id_length")
        .histogram(
            new NormalHistogram(NormalHistogram.linear(0, 5, 10))
        )
        .create();
  }

  @GetMapping("/reactive/salary/{userId}")
  public Mono<List<SalaryDto>> getsalaries(@PathVariable final String userId) {
    distributionSummary.record(userId.length());
    return salaryRepository.getSalary(userId);
  }

  @PostMapping(value = "/reactive/salary", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<SalaryDto> addSalary(@RequestBody SalaryDto salary) {
    return postTimer.record(
        () -> salaryRepository.addSalary(salary)
    );
  }

}
