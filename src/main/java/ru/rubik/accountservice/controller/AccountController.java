package ru.rubik.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;
import ru.rubik.accountservice.request.AmountAddRequest;
import ru.rubik.accountservice.service.AccountService;

import javax.jms.Message;

@RestController
public class AccountController {
    private final AccountService accountService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public AccountController(AccountService accountService, JmsTemplate jmsTemplate) {
        this.accountService = accountService;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/amount/{id}")
    public Long getAmount(@PathVariable Integer id) {
        return accountService.getAmount(id);
    }

    @PostMapping("/amount")
    public void addAmount(@RequestBody AmountAddRequest request) {
        // тут пробовал через jms реализовать, но это, как мне кажется, долго
        jmsTemplate.convertAndSend("addAmount", request);
//        accountService.addAmount(request.getId(), request.getAmount());
    }
}
