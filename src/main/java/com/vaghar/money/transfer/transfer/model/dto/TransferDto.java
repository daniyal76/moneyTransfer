package com.vaghar.money.transfer.transfer.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferDto {
    @NotNull
    private String sourceAccount;
    @NotNull
    private String destinationAccount;
    @NotNull
    private BigDecimal amount;
}
