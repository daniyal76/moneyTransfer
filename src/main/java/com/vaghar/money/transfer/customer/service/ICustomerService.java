package com.vaghar.money.transfer.customer.service;

import com.vaghar.money.transfer.customer.model.dto.CustomerDto;
import com.vaghar.money.transfer.customer.model.dto.CustomerOut;
import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import com.vaghar.money.transfer.customer.model.enums.CustomerType;

import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> getAll(CustomerType customerType, Integer id);

    CustomerEntity getById(Integer id);

    CustomerEntity save(CustomerDto dto);

    void update(Integer id, CustomerDto dto);

    void delete(Integer id);
}
