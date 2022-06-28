package com.sweetpoint.demo.service;

import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.ProductDto;
import com.sweetpoint.demo.domain.dto.UserDto;
import com.sweetpoint.demo.repository.ProductRepository;
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
public class ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
                        .image(productDao.getImage())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.SUCCESS, productDtoList, HttpStatus.OK);
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
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            ProductDao productDao = productDaoOptional.get();

            return ResponseUtil.build(ConstantApp.SUCCESS, ProductDto.builder()
                    .id(productDao.getId())
                    .productName(productDao.getProductName())
                    .denom(productDao.getDenom())
                    .provider(productDao.getProvider())
                    .descriptions(productDao.getDescriptions())
                    .points(productDao.getPoints())
                    .stock(productDao.getStock())
                    .image(productDao.getImage())
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting product by id, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createNewProduct(ProductDto productDto) {
        try {
            if(!Objects.equals(productDto.getStock(), 0)){
                ProductDao productDao = ProductDao.builder()
                        .productName(productDto.getProductName())
                        .denom(productDto.getDenom())
                        .provider(productDto.getProvider())
                        .descriptions(productDto.getDescriptions())
                        .points(productDto.getPoints())
                        .stock(productDto.getStock())
                        .image(productDto.getImage())
                        .build();
                productDao = productRepository.save(productDao);

                return ResponseUtil.build(ConstantApp.SUCCESS, ProductDto.builder()
                        .id(productDao.getId())
                        .productName(productDao.getProductName())
                        .denom(productDao.getDenom())
                        .provider(productDao.getProvider())
                        .descriptions(productDao.getDescriptions())
                        .points(productDao.getPoints())
                        .stock(productDao.getStock())
                        .image(productDao.getImage())
                        .build(), HttpStatus.OK);
            }
            return ResponseUtil.build(ConstantApp.INVALID_DATA, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            log.error("Get an error create new product, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateProduct(Long id, ProductDto request){
        try {
            log.info("Updating product with id : {}",id);
            Optional<ProductDao> productDaoOptional = productRepository.findById(id);
            if (productDaoOptional.isEmpty()) {
                log.info("Product not found");
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            if (!Objects.equals(request.getProductName(), "") && request.getProductName() != null) {
                productDaoOptional.get().setProductName(request.getProductName());
            }

            if (!Objects.equals(request.getDenom(), "") || !Objects.equals(request.getDenom(), 0) && request.getDenom() != null) {
                productDaoOptional.get().setDenom(request.getDenom());
            }

            if (!Objects.equals(request.getProvider(), "") && request.getProvider() != null) {
                productDaoOptional.get().setProvider(request.getProvider());
            }

            if (!Objects.equals(request.getDescriptions(), "") && request.getDescriptions() != null) {
                productDaoOptional.get().setDescriptions(request.getDescriptions());
            }

            if (!Objects.equals(request.getPoints(), "") && request.getPoints() != null) {
                productDaoOptional.get().setPoints(request.getPoints());
            }

            if (!Objects.equals(request.getStock(), "") || !Objects.equals(request.getStock(), 0) && request.getPoints() != null) {
                productDaoOptional.get().setPoints(request.getPoints());
            }

            if (!Objects.equals(request.getImage(), "") && request.getImage() != null) {
                productDaoOptional.get().setImage(request.getImage());
            }

            productRepository.save(productDaoOptional.get());

            return ResponseUtil.build(ConstantApp.DATA_UPDATED, ProductDto.builder()
                    .id(productDaoOptional.get().getId())
                    .productName(productDaoOptional.get().getProductName())
                    .denom(productDaoOptional.get().getDenom())
                    .provider(productDaoOptional.get().getProvider())
                    .descriptions(productDaoOptional.get().getDescriptions())
                    .points(productDaoOptional.get().getPoints())
                    .stock(productDaoOptional.get().getStock())
                    .image(productDaoOptional.get().getImage())
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
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            productRepository.delete(productDaoOptional.get());
            return ResponseUtil.build(ConstantApp.SUCCESS, null, HttpStatus.OK);

        }catch (Exception e) {
            log.error("Got an error deleting product, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> redeemProduct(Long id, HttpServletRequest request){
        try {
            log.info("Redeeming product, id : {}", id);
            Optional<ProductDao> productDaoOptional = productRepository.findById(id);

            if (productDaoOptional.isEmpty()){
                return ResponseUtil.build(ConstantApp.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            String bearerToken = request.getHeader("Authorization");
            String token = bearerToken.substring(7);

            Optional<UserDao> userDaoOptional = userRepository.findById(jwtTokenProvider.getId(token));

            ProductDao productDao = productDaoOptional.get();

            UserDao userDao = userDaoOptional.get();

            if(userDao.getPoint() - productDao.getPoints() >= 0){
                userDao.setPoint(userDao.getPoint() - productDao.getPoints());
                userDao = userRepository.save(userDao);

                productDao.setStock(productDao.getStock() - 1);
                productDao = productRepository.save(productDao);

                if(productDao.getStock() <= 0){
                    productRepository.delete(productDao);
                }

                return ResponseUtil.build(ConstantApp.SUCCESS, UserDto.builder()
                        .id(userDao.getId())
                        .point(userDao.getPoint())
                        .build(), HttpStatus.OK);
            }
            return ResponseUtil.build(ConstantApp.INSUFFICIENT_POINT, null, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("Got an error when redeeming product by id, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
