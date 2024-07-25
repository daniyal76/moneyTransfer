package com.vaghar.money.transfer.customer.controller;

import com.vaghar.money.transfer.customer.model.dto.CustomerDto;
import com.vaghar.money.transfer.customer.model.dto.CustomerOut;
import com.vaghar.money.transfer.customer.model.enums.CustomerType;
import com.vaghar.money.transfer.customer.model.mapper.CustomerMapper;
import com.vaghar.money.transfer.customer.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers", consumes = "application/json")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final ICustomerService service;
    private final CustomerMapper mapper;

    @GetMapping("/legals")
    public List<CustomerOut> getAllLegalCustomers(Integer id) {
        return service.getAll(CustomerType.LEGAL, id).stream()
                .map(mapper::mapToOut)
                .collect(Collectors.toList());
    }

    @GetMapping("/reals")
    public List<CustomerOut> getAllRealCustomers(Integer id) {
        return service.getAll(CustomerType.REAL, id).stream()
                .map(mapper::mapToOut)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CustomerOut getById(@PathVariable Integer id) {
        return mapper.mapToOut(service.getById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody CustomerDto dto) {
        service.save(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody CustomerDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
