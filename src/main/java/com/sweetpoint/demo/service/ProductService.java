package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dto.ProductDto;
import com.sweetpoint.demo.repository.ProductRepository;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Object> getAllProduct(){
        try {
            log.info("Getting all product's information");
            List<ProductDao> productDaoList = productRepository.findAll();
            List<ProductDto> productDtoList = new ArrayList<>();

            for (ProductDao productDao: productDaoList){
                productDtoList.add(ProductDto.builder()
                        .id(productDao.getId())
                        .productName(productDao.getProductName())
                        .denom(productDao.getDenom())
                        .provider(productDao.getProvider())
                        .descriptions(productDao.getDescriptions())
                        .points(productDao.getPoints())
                        .stock(productDao.getStock())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.KEY_FOUND, productDtoList, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Got an error when getting all product's information, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> getProductById(Long id){
        try {
            log.info("Getting product by id, id : {}", id);
            Optional<ProductDao> productDaoOptional = productRepository.findById(id);


            if (productDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            ProductDao productDao = productDaoOptional.get();

            return ResponseUtil.build(ConstantApp.KEY_FOUND, ProductDto.builder()
                    .id(productDao.getId())
                    .productName(productDao.getProductName())
                    .denom(productDao.getDenom())
                    .provider(productDao.getProvider())
                    .descriptions(productDao.getDescriptions())
                    .points(productDao.getPoints())
                    .stock(productDao.getStock())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting product by id, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewProduct(ProductDto productDto) {
        try {
            ProductDao productDao = ProductDao.builder()
                    .productName(productDto.getProductName())
                    .denom(productDto.getDenom())
                    .provider(productDto.getProvider())
                    .descriptions(productDto.getDescriptions())
                    .points(productDto.getPoints())
                    .stock(productDto.getStock())
                    .build();
            productDao = productRepository.save(productDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, ProductDto.builder()
                    .id(productDao.getId())
                    .productName(productDao.getProductName())
                    .denom(productDao.getDenom())
                    .provider(productDao.getProvider())
                    .descriptions(productDao.getDescriptions())
                    .points(productDao.getPoints())
                    .stock(productDao.getStock())
                    .build(), HttpStatus.OK);
        } catch (Exception e){
            log.error("Get an error create new product, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateProduct(Long id, ProductDto request){
        try {
            log.info("Updating product with id : {}",id);
            Optional<ProductDao> productDaoOptional = productRepository.findById(id);
            if (productDaoOptional.isEmpty()) {
                log.info("Product not found");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            ProductDao productDao = productDaoOptional.get();
            productDao.setProductName(request.getProductName());
            productDao.setDenom(request.getDenom());
            productDao.setProvider(request.getProvider());
            productDao.setDescriptions(request.getDescriptions());
            productDao.setPoints(request.getPoints());
            productDao.setStock(request.getStock());
            productRepository.save(productDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, ProductDto.builder()
                    .id(productDao.getId())
                    .productName(productDao.getProductName())
                    .denom(productDao.getDenom())
                    .provider(productDao.getProvider())
                    .descriptions(productDao.getDescriptions())
                    .points(productDao.getPoints())
                    .stock(productDao.getStock())
                    .build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Got an error when updating product's information. error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        try {
            log.info("Deleting product with id : {} ", id);
            Optional<ProductDao> productDaoOptional = productRepository.findById(id);
            if (productDaoOptional.isEmpty()) {
                log.info("Product not found!");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            productRepository.delete(productDaoOptional.get());
            return ResponseUtil.build(ConstantApp.KEY_FOUND, null, HttpStatus.OK);

        }catch (Exception e) {
            log.error("Got an error deleting product, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
