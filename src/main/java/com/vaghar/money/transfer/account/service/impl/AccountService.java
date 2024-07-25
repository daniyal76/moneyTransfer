package com.vaghar.money.transfer.account.service.impl;

import com.vaghar.money.transfer.account.model.dto.AccountDto;
import com.vaghar.money.transfer.account.model.entity.AccountEntity;
import com.vaghar.money.transfer.account.model.mapper.AccountMapper;
import com.vaghar.money.transfer.account.repository.AccountRepository;
import com.vaghar.money.transfer.account.service.IAccountService;
import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import com.vaghar.money.transfer.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository repository;
    private final ICustomerService customerService;
    private final AccountMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AccountEntity> getAll(Integer customerId, String number) {
        AccountEntity filter = new AccountEntity();
        filter.setCustomerId(customerId);
        filter.setNumber(number);
        return repository.findAll(Example.of(filter));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountEntity getEntityById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));
    }

    @Override
    @Transactional
    public AccountEntity save(AccountDto dto) {
        CustomerEntity customer = customerService.getById(dto.getCustomerId());
        AccountEntity entity = mapper.mapToEntity(dto);
        entity.setCustomer(customer);
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void updateBalance(String id, BigDecimal balance) {
        AccountEntity entity = repository.getByIdLockMode(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));
        entity.setBalance(entity.getBalance().add(balance));
        checkAccountBalance(entity);
        repository.save(entity);
    }

    private void checkAccountBalance(AccountEntity entity) {
        if (entity.getBalance().compareTo(BigDecimal.valueOf(10000)) <= 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "insufficient account balance");
        }
    }

}
