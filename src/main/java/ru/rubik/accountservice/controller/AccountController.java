package ru.rubik.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import ru.rubik.accountservice.request.AmountAddRequest;
import ru.rubik.accountservice.service.AccountService;
import ru.rubik.accountservice.service.StatsService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final JmsTemplate jmsTemplate;
    private final StatsService statsService;

    @Autowired
    public AccountController(AccountService accountService, JmsTemplate jmsTemplate, StatsService statsService) {
        this.accountService = accountService;
        this.jmsTemplate = jmsTemplate;
        this.statsService = statsService;
    }

    @GetMapping("/amount/{id}")
    public Long getAmount(@PathVariable Integer id) {
        statsService.increaseGetAmountRequestCount();
        statsService.markGetMountRequest();
        return accountService.getAmount(id);
    }

    @PostMapping("/amount")
    public void addAmount(@RequestBody AmountAddRequest request) {
        statsService.increaseAddAmountRequestCount();
        statsService.markAddMountRequest();
        jmsTemplate.convertAndSend("addAmount", request);
//        accountService.addAmount(request.getId(), request.getAmount());
    }
}
