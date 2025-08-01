package com.banklite.controller;

import com.banklite.model.Account;
import com.banklite.model.Customer;
import com.banklite.service.AccountService;
import com.banklite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        Long customerId = account.getCustomer().getId();
        return customerService.findById(customerId)
                .map(c -> {
                    account.setCustomer(c);
                    return ResponseEntity.ok(accountService.save(account));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Account> findByCustomer(@PathVariable Long customerId) {
        return accountService.findByCustomerId(customerId);
    }
}