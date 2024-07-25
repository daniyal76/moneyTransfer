package com.vaghar.money.transfer.account.repository;

import com.vaghar.money.transfer.account.model.entity.AccountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query("select a from AccountEntity a where a.id =:id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountEntity> getByIdLockMode(@Param("id") String id);
}
