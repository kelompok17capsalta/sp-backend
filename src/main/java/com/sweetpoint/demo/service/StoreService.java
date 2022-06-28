package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.StoreDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.StoreDto;
import com.sweetpoint.demo.domain.dto.UserDto;
import com.sweetpoint.demo.repository.StoreRepository;
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
public class StoreService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private StoreRepository storeRepository;

    public ResponseEntity<Object> getAllStore(){
        try{
            log.info("Getting all store product's information");
            List<StoreDao> storeDaoList = storeRepository.findAll();
            List<StoreDto> storeDtoList = new ArrayList<>();

            for(StoreDao storeDao: storeDaoList){
                storeDtoList.add(StoreDto.builder()
                        .id(storeDao.getId())
                        .storeName(storeDao.getStoreName())
                        .description(storeDao.getDescription())
                        .points(storeDao.getPoints())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.SUCCESS, storeDtoList, HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting all store's information, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStoreById(Long id){
        try{
            log.info("Getting store product by id,id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);

            if (storeDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null ,HttpStatus.BAD_REQUEST);
            }
            StoreDao storeDao = storeDaoOptional.get();

            return ResponseUtil.build(ConstantApp.SUCCESS, StoreDto.builder()
                    .id(storeDao.getId())
                    .storeName(storeDao.getStoreName())
                    .description(storeDao.getDescription())
                    .points(storeDao.getPoints())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting store product by id, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewStore(StoreDto storeDto){
        try{
            if(!Objects.equals(storeDto.getPoints(), 0)){
                StoreDao storeDao = StoreDao.builder()
                        .storeName(storeDto.getStoreName())
                        .description(storeDto.getDescription())
                        .points(storeDto.getPoints())
                        .build();
                storeDao = storeRepository.save(storeDao);

                return ResponseUtil.build(ConstantApp.SUCCESS, StoreDto.builder()
                        .id(storeDao.getId())
                        .storeName(storeDao.getStoreName())
                        .description(storeDao.getDescription())
                        .points(storeDao.getPoints())
                        .build(), HttpStatus.OK);
            }
            return ResponseUtil.build(ConstantApp.INVALID_DATA, null, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("Get an error create new store, Error: {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateStore(Long id, StoreDto request){
        try {
            log.info("Updating store product with id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);
            if (storeDaoOptional.isEmpty()){
                log.info("Store product not found");
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }

            if (!Objects.equals(request.getStoreName(), "") && request.getStoreName() != null) {
                storeDaoOptional.get().setStoreName(request.getStoreName());
            }

            if (!Objects.equals(request.getDescription(), "") && request.getDescription() != null) {
                storeDaoOptional.get().setDescription(request.getDescription());
            }

            if (!Objects.equals(request.getPoints(), "") || !Objects.equals(request.getPoints(), 0) && request.getPoints() != null) {
                storeDaoOptional.get().setPoints(request.getPoints());
            }

            storeRepository.save(storeDaoOptional.get());

            return ResponseUtil.build(ConstantApp.SUCCESS, StoreDto.builder()
                    .id(storeDaoOptional.get().getId())
                    .storeName(storeDaoOptional.get().getStoreName())
                    .description(storeDaoOptional.get().getDescription())
                    .points(storeDaoOptional.get().getPoints())
                    .build(),HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when updating store product's information, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteStore(Long id){
        try {
            log.info("Deleting store product with id : {}",id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);
            if (storeDaoOptional.isEmpty()){
                log.info("Store product not found");
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND,null,HttpStatus.BAD_REQUEST);
            }
            storeRepository.delete(storeDaoOptional.get());
            return ResponseUtil.build(ConstantApp.SUCCESS,null,HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when deleting store product, error : {}",e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> purchaseProduct(Long id, HttpServletRequest request){
        try {
            log.info("Purchasing store product, id : {}", id);
            Optional<StoreDao> storeDaoOptional = storeRepository.findById(id);

            if (storeDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            String bearerToken = request.getHeader("Authorization");
            String token = bearerToken.substring(7);

            Optional<UserDao> userDaoOptional = userRepository.findById(jwtTokenProvider.getId(token));

            StoreDao storeDao = storeDaoOptional.get();

            UserDao userDao = userDaoOptional.get();
            userDao.setPoint(userDao.getPoint() + storeDao.getPoints());
            userDao = userRepository.save(userDao);

            return ResponseUtil.build(ConstantApp.PURCHASE_COMPLETED, UserDto.builder()
                    .id(userDao.getId())
                    .point(userDao.getPoint())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when purchasing store product by id, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
