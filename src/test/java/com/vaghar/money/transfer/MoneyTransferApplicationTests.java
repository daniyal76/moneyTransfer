package com.vaghar.money.transfer;

import com.vaghar.money.transfer.account.model.dto.AccountDto;
import com.vaghar.money.transfer.account.model.entity.AccountEntity;
import com.vaghar.money.transfer.account.service.IAccountService;
import com.vaghar.money.transfer.customer.model.dto.CustomerDto;
import com.vaghar.money.transfer.customer.model.entity.CustomerEntity;
import com.vaghar.money.transfer.customer.model.enums.CustomerType;
import com.vaghar.money.transfer.customer.service.ICustomerService;
import com.vaghar.money.transfer.transfer.model.dto.TransferDto;
import com.vaghar.money.transfer.transfer.service.ITransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class MoneyTransferApplicationTests {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ITransferService transferService;

    @Test
    void successfulMoneyTransfer() {
        CustomerDto customer = new CustomerDto();
        customer.setName("danial");
        customer.setCustomerType(CustomerType.REAL);
        customer.setFamilyName("vaghar");
        customer.setPhoneNumber("09129241317");
        CustomerEntity customerEntity = customerService.save(customer);

        AccountDto firstAccountDto = new AccountDto();
        firstAccountDto.setNumber("134456-978859");
        firstAccountDto.setCustomerId(customerEntity.getId());
        firstAccountDto.setBalance(BigDecimal.valueOf(50000));
        AccountEntity firstAccountEntity = accountService.save(firstAccountDto);

        AccountDto secondAccountDto = new AccountDto();
        secondAccountDto.setNumber("134456-637100");
        secondAccountDto.setCustomerId(customerEntity.getId());
        secondAccountDto.setBalance(BigDecimal.valueOf(70000));
        AccountEntity secondAccountEntity = accountService.save(secondAccountDto);

        TransferDto transferDto = new TransferDto();
        transferDto.setSourceAccount(firstAccountEntity.getId());
        transferDto.setDestinationAccount(secondAccountEntity.getId());
        transferDto.setAmount(BigDecimal.valueOf(15000));
        transferService.transferMoney(transferDto);

        Assertions.assertEquals(35000, accountService.getEntityById(firstAccountEntity.getId()).getBalance().intValue());
        Assertions.assertEquals(85000, accountService.getEntityById(secondAccountEntity.getId()).getBalance().intValue());
    }


    @Test
    void successfulConcurrentMoneyTransfer() {
        CustomerDto customer = new CustomerDto();
        customer.setName("danial");
        customer.setCustomerType(CustomerType.REAL);
        customer.setFamilyName("vaghar");
        customer.setPhoneNumber("09129241317");
        CustomerEntity customerEntity = customerService.save(customer);

        AccountDto firstAccountDto = new AccountDto();
        firstAccountDto.setNumber("134456-978859");
        firstAccountDto.setCustomerId(customerEntity.getId());
        firstAccountDto.setBalance(BigDecimal.valueOf(50000));
        AccountEntity firstAccountEntity = accountService.save(firstAccountDto);

        AccountDto secondAccountDto = new AccountDto();
        secondAccountDto.setNumber("134456-637100");
        secondAccountDto.setCustomerId(customerEntity.getId());
        secondAccountDto.setBalance(BigDecimal.valueOf(70000));
        AccountEntity secondAccountEntity = accountService.save(secondAccountDto);

        List<TransferDto> transferList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TransferDto transferDto = new TransferDto();
            transferDto.setSourceAccount(firstAccountEntity.getId());
            transferDto.setDestinationAccount(secondAccountEntity.getId());
            transferDto.setAmount(BigDecimal.valueOf(15000));
            transferList.add(transferDto);
        }

        transferList.parallelStream().forEach(transfer -> {
            try {
                transferService.transferMoney(transfer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Assertions.assertEquals(20000, accountService.getEntityById(firstAccountEntity.getId()).getBalance().intValue());
        Assertions.assertEquals(100000, accountService.getEntityById(secondAccountEntity.getId()).getBalance().intValue());
    }

}
