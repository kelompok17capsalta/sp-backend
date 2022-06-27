package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dto.TransactionDto;
import com.sweetpoint.demo.repository.TransactionRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Object> getTransactionById(Long id){
        try{
            log.info("Getting transaction by id,id : {}",id);
            Optional<TransactionDao> transactionDaoOptional = transactionRepository.findById(id);

            if (transactionDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null ,HttpStatus.BAD_REQUEST);
            }
            TransactionDao transactionDao = transactionDaoOptional.get();

            return ResponseUtil.build(ConstantApp.KEY_FOUND, TransactionDto.builder()
                    .id(transactionDao.getId())
                    .transactionName(transactionDao.getTransactionName())
                    .value(transactionDao.getValue())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting transaction by id, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewTransaction(TransactionDto transactionDto){
        try{
            TransactionDao transactionDao = TransactionDao.builder()
                    .transactionName(transactionDto.getTransactionName())
                    .value(transactionDto.getValue())
                    .build();
            transactionDao = transactionRepository.save(transactionDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, TransactionDto.builder()
                    .id(transactionDao.getId())
                    .transactionName(transactionDao.getTransactionName())
                    .value(transactionDao.getValue())
                    .build(), HttpStatus.OK);
        } catch (Exception e){
            log.error("Get an error create new transaction, Error: {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateTransaction(Long id, TransactionDto request){
        try {
            log.info("Updating transaction with id : {}",id);
            Optional<TransactionDao> transactionDaoOptional = transactionRepository.findById(id);
            if (transactionDaoOptional.isEmpty()){
                log.info("Transaction not found");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            TransactionDao transactionDao = transactionDaoOptional.get();
            transactionDao.setTransactionName(request.getTransactionName());
            transactionDao.setValue(request.getValue());
            transactionRepository.save(transactionDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, TransactionDto.builder()
                    .id(transactionDao.getId())
                    .transactionName(transactionDao.getTransactionName())
                    .value(transactionDao.getValue())
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
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            transactionRepository.delete(transactionDaoOptional.get());
            return ResponseUtil.build(ConstantApp.KEY_FOUND,null,HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when deleting transaction, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
