package ru.rubik.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rubik.accountservice.service.StatsService;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {
    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/requests/addAmount")
    public Long getAddRequestsCount() {
        return statsService.getAddRequestsCount();
    }

    @GetMapping("/requests/getAmount")
    public Long getGetRequestsCount() {
        return statsService.getGetRequestsCount();
    }

    @GetMapping("/metrics/addAmount")
    public Double getMetricsOfAddAmount() {
        return statsService.getOneMinuteRateOfAddMountRequest();
    }

    @GetMapping("/metrics/getAmount")
    public Double getMetricsOfGetAmount() {
        return statsService.getOneMinuteRateOfAddMountRequest();
    }

    @PostMapping("/reset")
    public HttpStatus resetStats() {
        statsService.resetStats();
        return HttpStatus.ACCEPTED;
    }
}
