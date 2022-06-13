package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.domain.dto.StoreDto;
import com.sweetpoint.demo.domain.dto.TransactionDto;
import com.sweetpoint.demo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody TransactionDto transaction){
        return transactionService.createNewTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody TransactionDto transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }
}
