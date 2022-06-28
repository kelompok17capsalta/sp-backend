package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.StoreDto;
import com.sweetpoint.demo.service.StoreService;

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
@RequestMapping("v1/store")
public class StoreController {
    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return storeService.getAllStore();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(Principal principal, @RequestBody StoreDto store){

        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")){
            return storeService.createNewStore(store);
        }

        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable Long id, @RequestBody StoreDto store) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")){
            return storeService.updateStore(id, store);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(Principal principal, @PathVariable Long id) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")){
            return storeService.deleteStore(id);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/purchase/{id}")
    public ResponseEntity<Object> purchase(@PathVariable Long id, HttpServletRequest request) {
        return storeService.purchaseProduct(id, request);
    }
}
