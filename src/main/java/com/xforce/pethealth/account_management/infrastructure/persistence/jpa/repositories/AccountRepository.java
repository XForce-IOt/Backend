package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserName(String userName);
    List<Account> findByPetOwner(PetOwner petOwner);
    boolean existsByPetOwnerId(Long id);
}
