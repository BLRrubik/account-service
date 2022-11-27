package ru.rubik.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rubik.accountservice.dto.RequestCountDto;
import ru.rubik.accountservice.dto.RequestPerMinuteDto;
import ru.rubik.accountservice.service.StatsService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {
    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/requests/addAmount")
    public ResponseEntity<RequestCountDto> getAddRequestsCount() {
        return ResponseEntity.of(
                Optional.of(
                        new RequestCountDto(
                                statsService.getAddRequestsCount()
                        )
                )
        );
    }

    @GetMapping("/requests/getAmount")
    public ResponseEntity<RequestCountDto> getGetRequestsCount() {
        return ResponseEntity.of(
                Optional.of(
                        new RequestCountDto(
                                statsService.getGetRequestsCount()
                        )
                )
        );
    }

    @GetMapping("/metrics/addAmount")
    public ResponseEntity<RequestPerMinuteDto> getMetricsOfAddAmount() {
        return ResponseEntity.of(
                Optional.of(
                        new RequestPerMinuteDto(
                                statsService.getOneMinuteRateOfAddMountRequest()
                        )
                )
        );
    }

    @GetMapping("/metrics/getAmount")
    public ResponseEntity<RequestPerMinuteDto> getMetricsOfGetAmount() {
        return ResponseEntity.of(
                Optional.of(
                        new RequestPerMinuteDto(
                                statsService.getOneMinuteRateOfGetMountRequest()
                        )
                )
        );
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetStats() {
        statsService.resetStats();
        return ResponseEntity.ok("Stats success reset");
    }
}
