package com.vaghar.money.transfer.customer.model.mapper;

import com.vaghar.money.transfer.customer.model.dto.CustomerDto;
import com.vaghar.money.transfer.customer.model.dto.CustomerOut;
import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto mapToDto(CustomerEntity entity);

    CustomerEntity mapToEntity(CustomerDto dto);

    void updateCustomerFromCustomerDto(CustomerDto dto, @MappingTarget CustomerEntity entity);

    CustomerOut mapToOut(CustomerEntity entity);
}
