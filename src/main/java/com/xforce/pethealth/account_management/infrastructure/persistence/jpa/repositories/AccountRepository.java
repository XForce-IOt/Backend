package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //List<Account> findByAccountId(Long id);
    List<Account> findByUserName(String userName);
    //List<Account> findByAccountUserName(String userName);
    List<Account> findByPetOwnerId(Long petOwnerId);
}
