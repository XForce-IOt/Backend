package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.aggregate.Account;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.AccountService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.exception.ValidationException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final PetOwnerRepository petOwnerRepository;
    public AccountController(AccountService accountService, AccountRepository accountRepository, PetOwnerRepository petOwnerRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.petOwnerRepository = petOwnerRepository;
    }


    @Transactional(readOnly = true)
    @GetMapping("accounts/filterByUserName")
    public ResponseEntity<List<Account>> getAccountByUserName(@RequestParam String userName){
        return new ResponseEntity<>(accountRepository.findByUserName(userName), HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/pet-owner/{id}/accounts")
    public ResponseEntity<List<Account>> getAccountsByPetOwnerId(@PathVariable Long id){
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        return new ResponseEntity<>(accountRepository.findByPetOwner(petOwner), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }
    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){
        Account account = accountRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Account not found with id: " + id));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @PutMapping("accounts/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody Account account){
        boolean isAccountExist = accountService.isAccountExist(id);
        if(isAccountExist){
            validateAccount(account);
            account.setId(id);
            accountService.updateAccount(account);
            return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not updated");
        }
    }
    @DeleteMapping("accounts/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id){
        if(accountService.isAccountExist(id)){
            accountService.deleteAccount(id);
            return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not deleted");
        }
    }
    private void validateAccount(Account account){
        if(account.getUserName() == null || account.getUserName().trim().isEmpty()){
            throw new ValidationException("Account user name is required");
        }
        if(account.getPaymentMethod() == null || account.getPaymentMethod().trim().isEmpty()){
            throw new ValidationException("Account payment method is required");
        }
        if(account.getPaymentDate() == null || account.getPaymentDate().trim().isEmpty()){
            throw new ValidationException("Account payment date is required");
        }
    }
}
