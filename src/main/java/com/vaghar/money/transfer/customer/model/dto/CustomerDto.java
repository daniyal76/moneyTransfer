package com.vaghar.money.transfer.customer.model.dto;

import com.vaghar.money.transfer.customer.model.enums.CustomerType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNumber;
    private String faxNumber;
    private String familyName;
    @NotNull
    private CustomerType customerType;
}
