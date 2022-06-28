package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.ProductDto;
import com.sweetpoint.demo.service.ProductService;
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
@RequestMapping("v1/product")
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(Principal principal, @RequestBody ProductDto product) {

        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")) {
            return productService.createNewProduct(product);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable Long id, @RequestBody ProductDto product) {

        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")) {
            return productService.updateProduct(id, product);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(Principal principal, @PathVariable Long id) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());
        if (user.getRole().equals("Admin")) {
            return productService.deleteProduct(id);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/redeem/{id}")
    public ResponseEntity<Object> redeem(@PathVariable Long id, HttpServletRequest request) {
        return productService.redeemProduct(id, request);
    }
}
