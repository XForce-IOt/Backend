package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Long id);
    List<Account> getAllAccounts();
    Account getAccount(Long id);
    boolean isAccountExist(Long id);
    Account createPayment(Account account);
}
