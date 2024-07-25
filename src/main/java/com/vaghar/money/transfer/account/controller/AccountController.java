package com.vaghar.money.transfer.account.controller;

import com.vaghar.money.transfer.account.model.dto.AccountDto;
import com.vaghar.money.transfer.account.model.dto.AccountOut;
import com.vaghar.money.transfer.account.model.mapper.AccountMapper;
import com.vaghar.money.transfer.account.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/accounts", consumes = "application/json")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService service;
    private final AccountMapper mapper;

    @GetMapping
    public List<AccountOut> getAll(Integer customerId, String number) {
        return service.getAll(customerId, number).stream()
                .map(mapper::mapToOut)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountOut getById(@PathVariable String id) {
        return mapper.mapToOut(service.getEntityById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody AccountDto dto) {
        service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
