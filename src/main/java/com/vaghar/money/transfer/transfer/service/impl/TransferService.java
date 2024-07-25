package com.vaghar.money.transfer.transfer.service.impl;

import com.vaghar.money.transfer.account.service.IAccountService;
import com.vaghar.money.transfer.transfer.model.dto.TransferDto;
import com.vaghar.money.transfer.transfer.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferService implements ITransferService {

    private final IAccountService accountService;

    @Override
    @Transactional
    public void transferMoney(TransferDto dto) {
        accountService.updateBalance(dto.getSourceAccount(), dto.getAmount().negate());
        accountService.updateBalance(dto.getDestinationAccount(), dto.getAmount());
    }
}
