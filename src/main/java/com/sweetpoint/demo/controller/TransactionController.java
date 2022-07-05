package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.service.TransactionService;
import com.sweetpoint.demo.service.UserService;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(HttpServletRequest request){
        return transactionService.getUserTransaction(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody TransactionDto transaction){
        return transactionService.createNewTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable Long id, @RequestBody TransactionDto transaction) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")){
            return transactionService.updateTransaction(id, transaction);
        }

        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(Principal principal, @PathVariable Long id) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")){
            return transactionService.deleteTransaction(id);
        }

        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }
}
