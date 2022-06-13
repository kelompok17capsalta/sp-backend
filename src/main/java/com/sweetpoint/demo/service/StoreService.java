package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.StoreDao;
import com.sweetpoint.demo.domain.dto.StoreDto;
import com.sweetpoint.demo.repository.StoreRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public ResponseEntity<Object> getAllStore(){
        try{
            log.info("Getting all store's information");
            List<StoreDao> storeDaoList = storeRepository.findAll();
            List<StoreDto> storeDtoList = new ArrayList<>();

            for(StoreDao storeDao: storeDaoList){
                storeDtoList.add(StoreDto.builder()
                        .id(storeDao.getId())
                        .store_name(storeDao.getStore_name())
                        .description(storeDao.getDescription())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.KEY_FOUND, storeDtoList, HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting all store's information, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStoreById(Long id){
        try{
            log.info("Getting store by id,id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);

            if (storeDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null ,HttpStatus.BAD_REQUEST);
            }
            StoreDao storeDao = storeDaoOptional.get();

            return ResponseUtil.build(ConstantApp.KEY_FOUND, StoreDto.builder()
                    .id(storeDao.getId())
                    .store_name(storeDao.getStore_name())
                    .description(storeDao.getDescription())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting store by id, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewStore(StoreDto storeDto){
        try{
            StoreDao storeDao = StoreDao.builder()
                    .store_name(storeDto.getStore_name())
                    .description(storeDto.getDescription())
                    .build();
            storeRepository.save(storeDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND,StoreDto.builder()
                    .id(storeDao.getId())
                    .store_name(storeDao.getStore_name())
                    .description(storeDao.getDescription()), HttpStatus.OK);
        } catch (Exception e){
            log.error("Get an error create new store, Error: {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateStore(Long id, StoreDto request){
        try {
            log.info("Updating store with id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);
            if (storeDaoOptional.isEmpty()){
                log.info("Store not found");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            StoreDao storeDao = storeDaoOptional.get();
            storeDao.setStore_name(request.getStore_name());
            storeDao.setDescription(request.getDescription());
            storeRepository.save(storeDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, StoreDto.builder()
                    .id(storeDao.getId())
                    .store_name(storeDao.getStore_name())
                    .description(storeDao.getDescription())
                    .build(),HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when updating store's information, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteStore(Long id){
        try {
            log.info("Deleting store with id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);
            if (storeDaoOptional.isEmpty()){
                log.info("Store not found");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            storeRepository.delete(storeDaoOptional.get());
            return ResponseUtil.build(ConstantApp.KEY_FOUND,null,HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when deleting store, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
