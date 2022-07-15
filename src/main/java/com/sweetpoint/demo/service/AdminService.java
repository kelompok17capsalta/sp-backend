package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.request.ProductDto;
import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.domain.dto.request.UserDto;
import com.sweetpoint.demo.domain.dto.response.CountResponse;
import com.sweetpoint.demo.repository.ProductRepository;
import com.sweetpoint.demo.repository.TransactionRepository;
import com.sweetpoint.demo.repository.UserRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Object> getAllData(){
        try {
            log.info("Getting all data");

            int user = 0;
            int product = 0;
            int transaction = 0;

            List<UserDao> userDaoList = userRepository.findAll();

            for(UserDao userDao: userDaoList){
                if(userDao.getRole().equals("User")){
                    user++;
                }
            }

            List<ProductDao> productDaoList = productRepository.findAll();

            for (ProductDao productDao: productDaoList){
                product++;
            }

            List<TransactionDao> transactionDaoList = transactionRepository.findAll();

            for (TransactionDao transactionDao: transactionDaoList){
                transaction++;
            }

            return ResponseUtil.build(ConstantApp.SUCCESS, CountResponse.builder()
                    .user(user)
                    .product(product)
                    .transaction(transaction)
                    .build(), HttpStatus.OK);
        }catch (Exception e) {
            log.error("Got an error when getting all data, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
