package com.vaghar.money.transfer.account.model.mapper;

import com.vaghar.money.transfer.account.model.dto.AccountDto;
import com.vaghar.money.transfer.account.model.dto.AccountOut;
import com.vaghar.money.transfer.account.model.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "customer.name",target = "customerName")
    AccountOut mapToOut(AccountEntity entity);

    AccountEntity mapToEntity(AccountDto dto);

}
