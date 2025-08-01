package com.banklite.service;

import com.banklite.model.Account;
import com.banklite.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account save(Account account) {
        return repository.save(account);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public List<Account> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
}
