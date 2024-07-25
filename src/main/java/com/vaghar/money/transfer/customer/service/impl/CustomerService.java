package com.vaghar.money.transfer.customer.service.impl;

import com.vaghar.money.transfer.customer.model.dto.CustomerDto;
import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import com.vaghar.money.transfer.customer.model.enums.CustomerType;
import com.vaghar.money.transfer.customer.model.mapper.CustomerMapper;
import com.vaghar.money.transfer.customer.repository.CustomerRepository;
import com.vaghar.money.transfer.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    private static void checkBeforeSave(CustomerDto dto) {
        if (dto.getCustomerType().equals(CustomerType.REAL) && !StringUtils.hasText(dto.getFamilyName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "family name should be valued");
        }

        if (dto.getCustomerType().equals(CustomerType.LEGAL) && !StringUtils.hasText(dto.getFaxNumber())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "fax number should be valued");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerEntity> getAll(CustomerType customerType, Integer id) {
        CustomerEntity filter = new CustomerEntity();
        filter.setCustomerType(customerType);
        filter.setId(id);
        return repository.findAll(Example.of(filter));
    }

    @Override
    public CustomerEntity getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @Override
    @Transactional
    public CustomerEntity save(CustomerDto dto) {
        checkBeforeSave(dto);
        return repository.save(mapper.mapToEntity(dto));
    }

    @Override
    @Transactional
    public void update(Integer id, CustomerDto dto) {
        checkBeforeSave(dto);
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
        mapper.updateCustomerFromCustomerDto(dto, entity);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        CustomerEntity entity = repository.getReferenceById(id);
        checkBeforeDelete(entity);
        repository.delete(entity);
    }

    private void checkBeforeDelete(CustomerEntity entity) {
        if (!entity.getAccounts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "customer has account");
        }
    }
}
