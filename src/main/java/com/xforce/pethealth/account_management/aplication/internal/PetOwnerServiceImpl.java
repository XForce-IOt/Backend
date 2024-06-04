package com.xforce.pethealth.account_management.aplication.internal;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.PetOwnerService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetOwnerServiceImpl implements PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    private final AccountRepository accountRepository;
    public PetOwnerServiceImpl(AccountRepository accountRepository, PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<PetOwner> getAllPetOwners() {
        return petOwnerRepository.findAll();
    }
    @Override
    public PetOwner createPetOwner(Long accountId, PetOwner petOwner) {
        Account account = accountRepository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account not found with id: " + accountId));
        account.setPetOwner(petOwner);
        petOwner.setAccount(account);
        return petOwnerRepository.save(petOwner);
    }
    @Override
    public void updatePetOwner(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }
    @Override
    public boolean isPetOwnerExist(Long id) {
        return petOwnerRepository.existsById(id);
    }

}