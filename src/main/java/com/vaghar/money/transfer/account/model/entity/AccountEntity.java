package com.vaghar.money.transfer.account.model.entity;

import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "ACC_ACCOUNT")
@Getter
@Setter
public class AccountEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column
    private String number;

    @Column
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Integer customerId;
}
