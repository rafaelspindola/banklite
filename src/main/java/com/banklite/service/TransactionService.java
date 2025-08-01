package com.banklite.service;

import com.banklite.model.Account;
import com.banklite.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = getAccountOrThrow(accountId);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = getAccountOrThrow(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    private Account getAccountOrThrow(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }
}