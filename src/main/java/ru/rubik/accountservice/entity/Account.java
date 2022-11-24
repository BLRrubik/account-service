package ru.rubik.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@OptimisticLocking(type= OptimisticLockType.VERSION)
public class Account {
    @Id
    @Column(name = "account_id")
    private Integer id;

    @Column(name = "amount")
    private Long amount;

    @Version
    private Long version;
}
