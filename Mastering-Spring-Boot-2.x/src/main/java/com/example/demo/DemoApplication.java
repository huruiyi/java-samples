package com.example.demo;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.metrics.instrument.Clock;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.prometheus.PrometheusMeterRegistry;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnablePrometheusMetrics
public class DemoApplication {

    public static void main(String[] args) {
        //you can use
        // -Dspring.profiles.active=dev when starting app instead of hardcoding it
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean adminServletRegistrationBean() {
        return new ServletRegistrationBean<>(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    @Bean
    @ConditionalOnMissingBean
    public  CollectorRegistry collectorRegistry() {
        return new CollectorRegistry();
    }

    @Bean
    @Primary
    PrometheusMeterRegistry meterRegistry2() {
        return new PrometheusMeterRegistry(collectorRegistry());
    }

    @Bean(name = "meterRegistry1")
    public MeterRegistry meterRegistry(CollectorRegistry collectorRegistry) {
        //PrometheusMetricsExportAutoConfiguration
        return new PrometheusMeterRegistry(collectorRegistry, Clock.SYSTEM);
    }

    static {
        //HACK Avoids duplicate metrics registration in case of Spring Boot dev-tools restarts
        CollectorRegistry.defaultRegistry.clear();
    }

}
