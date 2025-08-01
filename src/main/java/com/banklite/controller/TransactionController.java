package com.banklite.controller;

import com.banklite.model.Account;
import com.banklite.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return transactionService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return transactionService.withdraw(id, amount);
    }
}
