package com.xforce.pethealth.account_management.aplication.internal;


import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import com.xforce.pethealth.account_management.domain.services.AccountService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository, PetOwnerRepository petOwnerRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
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

}
