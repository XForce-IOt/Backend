package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.entity.Account;
import com.xforce.pethealth.entity.PetOwner;
import com.xforce.pethealth.repository.AccountRepository;
import com.xforce.pethealth.repository.PetOwnerRepository;
import com.xforce.pethealth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Override
    public Account createAccount(Account account) {
        // Verifica que el petOwner no sea nulo y tenga un ID válido
        PetOwner petOwner = account.getPetOwner();
        if (petOwner == null || petOwner.getId() == null) {
            throw new IllegalArgumentException("PetOwner must be provided and must have a valid ID.");
        }

        // Verifica que el PetOwner exista en la base de datos
        Long petOwnerId = petOwner.getId();
        /*if (petOwnerId == null) {
            throw new IllegalArgumentException("PetOwner ID must be provided and cannot be null.");
        }*/

        PetOwner existingPetOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PetOwner ID."));

        // Asigna el PetOwner existente a la cuenta
        account.setPetOwner(petOwner);
        return accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public boolean isAccountExist(Long id) {
        return accountRepository.existsById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createPayment(Account account) {
        return accountRepository.save(account);
    }

}
