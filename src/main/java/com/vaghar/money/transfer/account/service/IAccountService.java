package com.vaghar.money.transfer.account.service;

import com.vaghar.money.transfer.account.model.dto.AccountDto;
import com.vaghar.money.transfer.account.model.entity.AccountEntity;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    AccountEntity getEntityById(String id);

    List<AccountEntity> getAll(Integer customerId, String number);

    AccountEntity save(AccountDto dto);

    void delete(String id);

    void updateBalance(String id, BigDecimal balance);
}
