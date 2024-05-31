package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //List<Account> findByAccountId(Long id);
    List<Account> findByUserName(String userName);
    //List<Account> findByAccountUserName(String userName);
    List<Account> findByPetOwnerId(Long petOwnerId);
}
