package ru.rubik.accountservice.controller.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.rubik.accountservice.request.AmountAddRequest;
import ru.rubik.accountservice.service.AccountService;

@Component
public class Receiver {
    @Autowired
    private AccountService accountService;

    @JmsListener(destination = "addAmount", containerFactory = "accountFactory")
    public void receiveAdd(AmountAddRequest request) {
        accountService.addAmount(request.getId(), request.getAmount());
    }

    @JmsListener(destination = "getAmount", containerFactory = "accountFactory")
    public Long receiveGet(Integer id) {
        return accountService.getAmount(id);
    }

}