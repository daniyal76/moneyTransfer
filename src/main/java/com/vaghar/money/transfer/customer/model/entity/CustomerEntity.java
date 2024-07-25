package com.vaghar.money.transfer.customer.model.entity;

import com.vaghar.money.transfer.account.model.entity.AccountEntity;
import com.vaghar.money.transfer.customer.model.enums.CustomerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USM_CUSTOMER")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(generator = "customer-id-generator")
    @GenericGenerator(name = "customer-id-generator", strategy = "com.vaghar.money.transfer.config.generator.CustomIdGenerator")
    private Integer id;

    @Column
    private String name;
    @Column
    private String phoneNumber;

    @Column
    private String faxNumber;

    @Column
    private String familyName;

    @Enumerated
    private CustomerType customerType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<AccountEntity> accounts = new HashSet<>();
}
