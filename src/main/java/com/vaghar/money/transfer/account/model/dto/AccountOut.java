package com.vaghar.money.transfer.account.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOut extends AccountDto {
    private String id;
    private String customerName;
}
