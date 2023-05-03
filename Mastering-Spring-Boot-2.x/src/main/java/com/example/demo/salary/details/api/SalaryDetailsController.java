package com.example.demo.salary.details.api;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.metrics.annotation.Timed;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SalaryDetailsController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalaryDetails salaryDetails;

    private LoadingCache<String, String> loader = CacheBuilder.newBuilder().maximumSize(100) //on prod should be higher
            .expireAfterWrite(10, TimeUnit.SECONDS).removalListener((RemovalListener<String, String>) removalNotification -> {
                log.info("entry from cache expired: " + removalNotification);
            }).build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    return salaryDetails.getInfoAboutSalary(key);
                }
            });

    @Autowired
    public SalaryDetailsController(SalaryDetails salaryDetails, MeterRegistry meterRegistry) {
        this.salaryDetails = salaryDetails;
        meterRegistry.gauge("cache_size", loader, v -> loader.size());

    }

    @Timed
    @RequestMapping(value = "/salary/salary-details/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    String getInfoAboutSalary(@PathVariable final String account) throws Exception {
        return loader.get(account);
    }
}