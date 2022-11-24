package ru.rubik.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rubik.accountservice.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
