package com.vaghar.money.transfer.transfer.controller;

import com.vaghar.money.transfer.transfer.model.dto.TransferDto;
import com.vaghar.money.transfer.transfer.service.ITransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transfer", consumes = "application/json")
@RequiredArgsConstructor
@Validated
public class TransferController {
    private final ITransferService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@Valid @RequestBody TransferDto dto) {
        service.transferMoney(dto);
    }
}
