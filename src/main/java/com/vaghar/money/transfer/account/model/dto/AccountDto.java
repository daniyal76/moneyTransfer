package com.vaghar.money.transfer.account.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {
    @NotEmpty
    private String number;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private Integer customerId;
}
