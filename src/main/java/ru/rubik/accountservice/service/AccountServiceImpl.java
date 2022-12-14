package ru.rubik.accountservice.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rubik.accountservice.entity.Account;
import ru.rubik.accountservice.repository.AccountRepository;

import javax.annotation.Resource;
import javax.persistence.LockModeType;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    private final Logger logger = LoggerFactory.getLogger("logger");
    private final AccountRepository accountRepository;

    @Resource(name = "accountServiceImpl")
    @Lazy
    private AccountServiceImpl self;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    @Cacheable(value = "accounts", key = "#id")
    public Long getAmount(Integer id) {

        Account account = accountRepository.findById(id)
                .orElseGet(() -> {
                    Account acc = new Account();
                    acc.setId(id);
                    acc.setAmount(0L);

                    return acc;
                });

        logger.info("Get amount from account with id " + id + " and amount " + account.getAmount());

        return account.getAmount();
    }

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public Account createAccount(Integer id) {
        logger.info("Creating account with id " + id);
        Account acc = new Account();
        acc.setId(id);
        acc.setAmount(0L);

        return accountRepository.save(acc);
    }

    @Override
    @Transactional
    public void addAmount(Integer id, Long value) {
        Account account = accountRepository.findById(id)
                .orElseGet(() -> self.createAccount(id));

        logger.info("Add account amount with id " + id +
                " " + account.getAmount() + " -> " + (account.getAmount() + value));

        account.setAmount(self.updateCache(id, account.getAmount() + value));

        accountRepository.save(account);
    }

    @CachePut(value = "accounts", key = "#id")
    public Long updateCache(Integer id, Long amount) {
        return amount;
    }
}


