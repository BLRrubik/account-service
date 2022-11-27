package ru.rubik.accountservice.config;

import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfig {
    @Bean
    public MetricRegistry getMetricRegistry() {
        return new MetricRegistry();
    }

}
