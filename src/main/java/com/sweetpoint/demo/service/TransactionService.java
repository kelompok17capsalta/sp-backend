package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.request.ProductDto;
import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.domain.dto.request.UserDto;
import com.sweetpoint.demo.repository.ProductRepository;
import com.sweetpoint.demo.repository.TransactionRepository;
import com.sweetpoint.demo.repository.UserRepository;
import com.sweetpoint.demo.security.JwtTokenProvider;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<Object> getAllTransaction(){
        try {
            log.info("Getting all product's information");
            List<TransactionDao> transactionDaoList = transactionRepository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();

            for (TransactionDao transactionDao: transactionDaoList){
                Optional<UserDao> userDaoOptional = userRepository.findById(transactionDao.getUser().getId());

                transactionDtoList.add(TransactionDto.builder()
                        .id(transactionDao.getId())
                        .created(transactionDao.getCreatedAt())
                        .descriptions(transactionDao.getDescriptions())
                        .status(transactionDao.getStatus())
                        .points(transactionDao.getPoints())
                        .user(UserDto.builder()
                                .username(userDaoOptional.get().getUsername())
                                .build())
                        .category(transactionDao.getCategory())
                        .credentials(transactionDao.getCredentials())
                        .provider(transactionDao.getProvider())
                        .denom(transactionDao.getDenom())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.SUCCESS, transactionDtoList, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Got an error when getting all transaction's information, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> getUserTransaction(HttpServletRequest request){
        try {
            log.info("Getting user's transactions");

            String bearerToken = request.getHeader("Authorization");
            String token = bearerToken.substring(7);

            Optional<UserDao> userToken = userRepository.findById(jwtTokenProvider.getId(token));

            List<TransactionDao> transactionDaoList = transactionRepository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();

            for (TransactionDao transactionDao: transactionDaoList){
                if(transactionDao.getUser().getId().equals(userToken.get().getId())){
                    Optional<UserDao> userDaoOptional = userRepository.findById(transactionDao.getUser().getId());

                    transactionDtoList.add(TransactionDto.builder()
                        .id(transactionDao.getId())
                        .created(transactionDao.getCreatedAt())
                        .descriptions(transactionDao.getDescriptions())
                        .status(transactionDao.getStatus())
                        .points(transactionDao.getPoints())
                        .user(UserDto.builder()
                                .username(userDaoOptional.get().getUsername())
                                .build())
                        .category(transactionDao.getCategory())
                        .credentials(transactionDao.getCredentials())
                        .provider(transactionDao.getProvider())
                        .denom(transactionDao.getDenom())
                        .build());
                }
            }
            return ResponseUtil.build(ConstantApp.SUCCESS, transactionDtoList, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Got an error when getting all transaction's information, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> getTransactionById(Long id){
        try{
            log.info("Getting transaction by id, id : {}", id);
            Optional<TransactionDao> transactionDaoOptional = transactionRepository.findById(id);

            if (transactionDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null ,HttpStatus.BAD_REQUEST);
            }
            TransactionDao transactionDao = transactionDaoOptional.get();

            return ResponseUtil.build(ConstantApp.SUCCESS, TransactionDto.builder()
                    .id(transactionDao.getId())
                    .created(transactionDao.getCreatedAt())
                    .descriptions(transactionDao.getDescriptions())
                    .status(transactionDao.getStatus())
                    .points(transactionDao.getPoints())
                    .user(UserDto.builder()
                            .username(transactionDao.getUser().getUsername())
                            .build())
                    .category(transactionDao.getCategory())
                    .credentials(transactionDao.getCredentials())
                    .provider(transactionDao.getProvider())
                    .denom(transactionDao.getDenom())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting transaction by id, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewTransaction(TransactionDto transactionDto){
        try{
            Optional<UserDao> userDaoOptional = Optional.ofNullable(userRepository.findByUsername(transactionDto.getUser().getUsername()));
            if(userDaoOptional.isEmpty()){
                return ResponseUtil.build("Username tidak ditemukan!", null, HttpStatus.BAD_REQUEST);
            }

            if(transactionDto.getStatus() == null || transactionDto.getStatus().equals("Pending") || !transactionDto.getStatus().equals("Pending")){
                if(transactionDto.getProduct() == null && transactionDto.getCategory().equals("shopping")){
                    TransactionDao transactionDao = TransactionDao.builder()
                            .descriptions(transactionDto.getDescriptions())
                            .status("Pending")
                            .points(transactionDto.getPoints())
                            .user(userDaoOptional.get())
                            .category(transactionDto.getCategory())
                            .credentials(transactionDto.getCredentials())
                            .provider(transactionDto.getProvider())
                            .denom(transactionDto.getDenom())
                            .build();
                    transactionDao = transactionRepository.save(transactionDao);

                    UserDao userDao = userDaoOptional.get();

                    userDao.setPoint(userDao.getPoint() + transactionDao.getPoints());
                    userRepository.save(userDao);

                    return ResponseUtil.build(ConstantApp.SUCCESS, TransactionDto.builder()
                            .id(transactionDao.getId())
                            .created(transactionDao.getCreatedAt())
                            .descriptions(transactionDao.getDescriptions())
                            .status(transactionDao.getStatus())
                            .points(transactionDao.getPoints())
                            .user(
                                    UserDto.builder()
                                            .username(userDaoOptional.get().getUsername())
                                            .build()
                            )
                            .category(transactionDao.getCategory())
                            .credentials(transactionDao.getCredentials())
                            .provider(transactionDao.getProvider())
                            .denom(transactionDao.getDenom())
                            .build(), HttpStatus.OK);
                }else if(!transactionDto.getCategory().equals("shopping") && !(transactionDto.getProduct() == null)){
                    Optional<ProductDao> productDaoOptional = productRepository.findById(transactionDto.getProduct().getId());
                    if(productDaoOptional.isEmpty()){
                        return ResponseUtil.build("Product tidak ditemukan!", null, HttpStatus.BAD_REQUEST);
                    }

                    TransactionDao transactionDao = TransactionDao.builder()
                            .descriptions(transactionDto.getDescriptions())
                            .status("Pending")
                            .points(transactionDto.getPoints())
                            .user(userDaoOptional.get())
                            .category(transactionDto.getCategory())
                            .credentials(transactionDto.getCredentials())
                            .provider(transactionDto.getProvider())
                            .denom(transactionDto.getDenom())
                            .product(productDaoOptional.get())
                            .build();
                    transactionDao = transactionRepository.save(transactionDao);

                    ProductDao productDao = productDaoOptional.get();

                    UserDao userDao = userDaoOptional.get();

                    if(userDao.getPoint() - transactionDao.getPoints() >= 0){
                        if(productDao.getStock() > 0){
                            userDao.setPoint(userDao.getPoint() - transactionDao.getPoints());
                            userRepository.save(userDao);

                            productDao.setStock(productDao.getStock() - 1);
                            productRepository.save(productDao);
                        }else{
                            return ResponseUtil.build("Stock product tidak mencukupi!", null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }else{
                        return ResponseUtil.build(ConstantApp.INSUFFICIENT_POINT, null, HttpStatus.BAD_REQUEST);
                    }

                    return ResponseUtil.build(ConstantApp.SUCCESS, TransactionDto.builder()
                            .id(transactionDao.getId())
                            .created(transactionDao.getCreatedAt())
                            .descriptions(transactionDao.getDescriptions())
                            .status(transactionDao.getStatus())
                            .points(transactionDao.getPoints())
                            .user(
                                    UserDto.builder()
                                            .username(userDaoOptional.get().getUsername())
                                            .build()
                            )
                            .category(transactionDao.getCategory())
                            .credentials(transactionDao.getCredentials())
                            .provider(transactionDao.getProvider())
                            .denom(transactionDao.getDenom())
                            .build(), HttpStatus.OK);
                }else{
                    return ResponseUtil.build(ConstantApp.INVALID_DATA, null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            log.error("Get an error creating new transaction, Error: {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateTransaction(Long id, TransactionDto request){
        try {
            log.info("Updating transaction with id : {}",id);
            Optional<TransactionDao> transactionDaoOptional = transactionRepository.findById(id);

            if (transactionDaoOptional.isEmpty()){
                log.info("Transaction not found");
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }

            Optional<UserDao> userDaoOptional = Optional.ofNullable(userRepository.findByUsername(request.getUser().getUsername()));
            if(userDaoOptional.isEmpty()){
                return ResponseUtil.build("Username tidak ditemukan!", null, HttpStatus.BAD_REQUEST);
            }

            Optional<ProductDao> productDaoOptional = productRepository.findById(request.getProduct().getId());
            if(productDaoOptional.isEmpty()){
                return ResponseUtil.build("Product tidak ditemukan!", null, HttpStatus.BAD_REQUEST);
            }

            if (!Objects.equals(request.getDescriptions(), "") && request.getDescriptions() != null) {
                transactionDaoOptional.get().setDescriptions(request.getDescriptions());
            }

            if (!Objects.equals(request.getStatus(), "") && request.getStatus() != null) {
                if(request.getStatus().equals("Pending")){
                    transactionDaoOptional.get().setStatus("Pending");
                }else if(request.getStatus().equals("Failed")){
                    transactionDaoOptional.get().setStatus("Failed");
                }else if(request.getStatus().equals("Success")){
                    transactionDaoOptional.get().setStatus("Success");
                }else{
                    return ResponseUtil.build("Masukkan status yang valid!", null, HttpStatus.BAD_REQUEST);
                }
            }

            if (request.getPoints() != null) {
                transactionDaoOptional.get().setPoints(request.getPoints());
            }

            if (request.getUser() != null) {
                transactionDaoOptional.get().setUser(userDaoOptional.get());
            }

            if (!Objects.equals(request.getCategory(), "") && request.getCategory() != null) {
                transactionDaoOptional.get().setCategory(request.getCategory());
            }

            if (!Objects.equals(request.getCredentials(), "") && request.getCredentials() != null) {
                transactionDaoOptional.get().setCredentials(request.getCredentials());
            }

            if (!Objects.equals(request.getProvider(), "") && request.getProvider() != null) {
                transactionDaoOptional.get().setProvider(request.getProvider());
            }

            if (request.getDenom() != null) {
                transactionDaoOptional.get().setDenom(request.getDenom());
            }

            if (request.getProduct() != null) {
                transactionDaoOptional.get().setProduct(productDaoOptional.get());
            }

            transactionRepository.save(transactionDaoOptional.get());

            return ResponseUtil.build(ConstantApp.DATA_UPDATED, TransactionDto.builder()
                    .id(transactionDaoOptional.get().getId())
                    .descriptions(transactionDaoOptional.get().getDescriptions())
                    .status(transactionDaoOptional.get().getStatus())
                    .points(transactionDaoOptional.get().getPoints())
                    .user(
                            UserDto.builder()
                                    .username(userDaoOptional.get().getUsername())
                                    .build()
                    )
                    .category(transactionDaoOptional.get().getCategory())
                    .credentials(transactionDaoOptional.get().getCredentials())
                    .provider(transactionDaoOptional.get().getProvider())
                    .denom(transactionDaoOptional.get().getDenom())
                    .build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when updating transaction's detail, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteTransaction(Long id){
        try {
            log.info("Deleting transaction with id : {}",id);
            Optional<TransactionDao> transactionDaoOptional = transactionRepository.findById(id);
            if (transactionDaoOptional.isEmpty()){
                log.info("Transaction not found");
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            transactionRepository.delete(transactionDaoOptional.get());
            return ResponseUtil.build(ConstantApp.SUCCESS,null,HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when deleting transaction, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
