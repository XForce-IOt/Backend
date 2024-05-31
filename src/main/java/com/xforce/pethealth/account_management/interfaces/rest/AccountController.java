package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.AccountService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    private final AccountRepository accountRepository;
    private final PetOwnerRepository petOwnerRepository;
    public AccountController(AccountRepository accountRepository, PetOwnerRepository petOwnerRepository) {
        this.accountRepository = accountRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){
        Account account = accountRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet not found with id: " + id));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            Account createdAccount = accountService.createAccount(account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Account createdAccount = accountService.createAccount(account);
        //return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        accountDetails.setId(id);
        accountService.updateAccount(accountDetails);
        return new ResponseEntity<>(accountDetails, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<Account>> getAccountsByUserName(@PathVariable String userName) {
        List<Account> accounts = accountRepository.findByUserName(userName);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/pet-owner/{id}")
    public ResponseEntity<List<Account>> getAccountsByPetOwnerId(@PathVariable Long id){
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        List<Account> accounts = accountRepository.findByPetOwnerId(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/pet-owners/{id}/subscriptions")
    public ResponseEntity<Account> createSubscription(@PathVariable Long id, @RequestBody Account account) {
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        account.setPetOwner(petOwner);
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountsByAccountId(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
