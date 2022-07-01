package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dto.request.ProductDto;
import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.repository.TransactionRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Object> getAllTransaction(){
        try {
            log.info("Getting all product's information");
            List<TransactionDao> transactionDaoList = transactionRepository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();

            for (TransactionDao transactionDao: transactionDaoList){
                transactionDtoList.add(TransactionDto.builder()
                        .id(transactionDao.getId())
                        .created(transactionDao.getCreatedAt())
                        .descriptions(transactionDao.getDescriptions())
                        .status(transactionDao.getStatus())
                        .points(transactionDao.getPoints())
                        .userId(transactionDao.getUserId())
                        .category(transactionDao.getCategory())
                        .credentials(transactionDao.getCredentials())
                        .provider(transactionDao.getProvider())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.SUCCESS, transactionDtoList, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Got an error when getting all transaction's information, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> getUserTransaction(Long id){
        try {
            log.info("Getting user's transactions");
            List<TransactionDao> transactionDaoList = transactionRepository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();

            for (TransactionDao transactionDao: transactionDaoList){
                if(transactionDao.getUserId().equals(id)){
                    transactionDtoList.add(TransactionDto.builder()
                        .id(transactionDao.getId())
                        .created(transactionDao.getCreatedAt())
                        .descriptions(transactionDao.getDescriptions())
                        .status(transactionDao.getStatus())
                        .points(transactionDao.getPoints())
                        .userId(transactionDao.getUserId())
                        .category(transactionDao.getCategory())
                        .credentials(transactionDao.getCredentials())
                        .provider(transactionDao.getProvider())
                        .productId(transactionDao.getProductId())
                        .build());

                    return ResponseUtil.build(ConstantApp.SUCCESS, transactionDtoList, HttpStatus.OK);
                }
            }

            return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.OK);
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
                    .userId(transactionDao.getUserId())
                    .category(transactionDao.getCategory())
                    .credentials(transactionDao.getCredentials())
                    .provider(transactionDao.getProvider())
                    .productId(transactionDao.getProductId())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting transaction by id, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewTransaction(TransactionDto transactionDto){
        try{
            if(transactionDto.getStatus() == null || transactionDto.getStatus().equals("Pending") || !transactionDto.getStatus().equals("Pending")){
                TransactionDao transactionDao = TransactionDao.builder()
                        .descriptions(transactionDto.getDescriptions())
                        .status("Pending")
                        .points(transactionDto.getPoints())
                        .userId(transactionDto.getUserId())
                        .category(transactionDto.getCategory())
                        .credentials(transactionDto.getCredentials())
                        .provider(transactionDto.getProvider())
                        .productId(transactionDto.getProductId())
                        .build();
                transactionDao = transactionRepository.save(transactionDao);

                return ResponseUtil.build(ConstantApp.SUCCESS, TransactionDto.builder()
                        .id(transactionDao.getId())
                        .created(transactionDao.getCreatedAt())
                        .descriptions(transactionDao.getDescriptions())
                        .status(transactionDao.getStatus())
                        .points(transactionDao.getPoints())
                        .userId(transactionDao.getUserId())
                        .category(transactionDao.getCategory())
                        .credentials(transactionDao.getCredentials())
                        .provider(transactionDao.getProvider())
                        .productId(transactionDao.getProductId())
                        .build(), HttpStatus.OK);
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

            if (request.getUserId() != null) {
                transactionDaoOptional.get().setUserId(request.getUserId());
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

            if (request.getProductId() != null) {
                transactionDaoOptional.get().setUserId(request.getProductId());
            }

            transactionRepository.save(transactionDaoOptional.get());

            return ResponseUtil.build(ConstantApp.DATA_UPDATED, TransactionDto.builder()
                    .id(transactionDaoOptional.get().getId())
                    .descriptions(transactionDaoOptional.get().getDescriptions())
                    .status(transactionDaoOptional.get().getStatus())
                    .points(transactionDaoOptional.get().getPoints())
                    .userId(transactionDaoOptional.get().getUserId())
                    .category(transactionDaoOptional.get().getCategory())
                    .credentials(transactionDaoOptional.get().getCredentials())
                    .provider(transactionDaoOptional.get().getProvider())
                    .productId(transactionDaoOptional.get().getProductId())
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
