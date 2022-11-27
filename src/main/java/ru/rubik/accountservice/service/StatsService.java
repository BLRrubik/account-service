package ru.rubik.accountservice.service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final MetricRegistry metricRegistry;
    private final Meter addAmountMeter;

    private final Meter getAmountMeter;
    private Long addRequestsCount = 0L;
    private Long getRequestsCount = 0L;

    public StatsService(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
        getAmountMeter = metricRegistry.meter("getAmount");
        addAmountMeter = metricRegistry.meter("addAmount");
    }


    public void markAddMountRequest() {
        addAmountMeter.mark();
    }

    public void markGetMountRequest() {
        getAmountMeter.mark();
    }

    public Double getOneMinuteRateOfAddMountRequest() {
        return addAmountMeter.getOneMinuteRate();
    }

    public Double getOneMinuteRateOfGetMountRequest() {
        return getAmountMeter.getOneMinuteRate();
    }

    public void increaseAddAmountRequestCount() {
        addRequestsCount++;
    }

    public void increaseGetAmountRequestCount() {
        getRequestsCount++;
    }

    public Long getAddRequestsCount() {
        return addRequestsCount;
    }

    public Long getGetRequestsCount() {
        return getRequestsCount;
    }

    public void resetStats() {
        addRequestsCount = 0L;
        getRequestsCount = 0L;
    }
}
