package com.vaghar.money.transfer.transfer.service.impl;

import com.vaghar.money.transfer.account.service.IAccountService;
import com.vaghar.money.transfer.transfer.model.dto.TransferDto;
import com.vaghar.money.transfer.transfer.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TransferService implements ITransferService {

    private final IAccountService accountService;

    @Override
    @Transactional
    public void transferMoney(TransferDto dto) {
        if (dto.getSourceAccount().equals(dto.getDestinationAccount())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "source and destination account cannot be the same");
        }
        accountService.updateBalance(dto.getSourceAccount(), dto.getAmount().negate());
        accountService.updateBalance(dto.getDestinationAccount(), dto.getAmount());
    }
}
