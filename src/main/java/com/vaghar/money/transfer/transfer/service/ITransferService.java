package com.vaghar.money.transfer.transfer.service;

import com.vaghar.money.transfer.transfer.model.dto.TransferDto;

public interface ITransferService {
    void transferMoney(TransferDto dto);
}
