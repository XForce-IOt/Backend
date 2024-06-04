package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;

import java.util.List;

public interface AccountService {
    boolean isAccountExist(Long id);
    Account createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Long id);
    List<Account> getAllAccounts();
    Account getAccount(Long id);
}
