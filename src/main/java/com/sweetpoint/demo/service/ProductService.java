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
                        .category(productDao.getCategory())
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
                    .category(productDao.getCategory())
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
            if(productDto.getStock() > 0){
                if(productDto.getCategory().equals("Cash Out")){
                    ProductDao productDao = ProductDao.builder()
                            .productName(productDto.getProductName())
                            .denom(productDto.getDenom())
                            .category("Cash Out")
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
                            .category(productDao.getCategory())
                            .descriptions(productDao.getDescriptions())
                            .points(productDao.getPoints())
                            .stock(productDao.getStock())
                            .image(productDao.getImage())
                            .build(), HttpStatus.OK);
                }

                if(productDto.getCategory().equals("E-Money")){
                    ProductDao productDao = ProductDao.builder()
                            .productName(productDto.getProductName())
                            .denom(productDto.getDenom())
                            .category("E-Money")
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
                            .category(productDao.getCategory())
                            .descriptions(productDao.getDescriptions())
                            .points(productDao.getPoints())
                            .stock(productDao.getStock())
                            .image(productDao.getImage())
                            .build(), HttpStatus.OK);
                }

                if(productDto.getCategory().equals("Pulsa")){
                    ProductDao productDao = ProductDao.builder()
                            .productName(productDto.getProductName())
                            .denom(productDto.getDenom())
                            .category("Pulsa")
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
                            .category(productDao.getCategory())
                            .descriptions(productDao.getDescriptions())
                            .points(productDao.getPoints())
                            .stock(productDao.getStock())
                            .image(productDao.getImage())
                            .build(), HttpStatus.OK);
                }

                if(productDto.getCategory().equals("Paket Data")){
                    ProductDao productDao = ProductDao.builder()
                            .productName(productDto.getProductName())
                            .denom(productDto.getDenom())
                            .category("Paket Data")
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
                            .category(productDao.getCategory())
                            .descriptions(productDao.getDescriptions())
                            .points(productDao.getPoints())
                            .stock(productDao.getStock())
                            .image(productDao.getImage())
                            .build(), HttpStatus.OK);
                }
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

            if (!Objects.equals(request.getDenom(), "") && request.getDenom() != null) {
                if(request.getDenom() > 0){
                    productDaoOptional.get().setDenom(request.getDenom());
                }else{
                    return ResponseUtil.build("Denom tidak boleh 0!", null, HttpStatus.BAD_REQUEST);
                }
            }

            if (!Objects.equals(request.getCategory(), "") && request.getCategory() != null) {
                if(request.getCategory().equals("Cash Out")){
                    productDaoOptional.get().setCategory("Cash Out");
                }else if(request.getCategory().equals("E-Money")){
                    productDaoOptional.get().setCategory("E-Money");
                }else if(request.getCategory().equals("Pulsa")){
                    productDaoOptional.get().setCategory("Pulsa");
                }else if(request.getCategory().equals("Paket Data")){
                    productDaoOptional.get().setCategory("Paket Data");
                }else{
                    return ResponseUtil.build("Masukkan kategori yang valid!", null, HttpStatus.BAD_REQUEST);
                }
            }

            if (!Objects.equals(request.getDescriptions(), "") && request.getDescriptions() != null) {
                productDaoOptional.get().setDescriptions(request.getDescriptions());
            }

            if (request.getPoints() != null) {
                if(request.getPoints() > 0){
                    productDaoOptional.get().setPoints(request.getPoints());
                }else{
                    return ResponseUtil.build("Point tidak boleh 0!", null, HttpStatus.BAD_REQUEST);
                }
            }

            if (request.getStock() != null) {
                if(request.getStock() > 0){
                    productDaoOptional.get().setStock(request.getStock());
                }else{
                    return ResponseUtil.build("Stok tidak boleh 0!", null, HttpStatus.BAD_REQUEST);
                }
            }

            if (!Objects.equals(request.getImage(), "") && request.getImage() != null) {
                productDaoOptional.get().setImage(request.getImage());
            }

            productRepository.save(productDaoOptional.get());

            return ResponseUtil.build(ConstantApp.DATA_UPDATED, ProductDto.builder()
                    .id(productDaoOptional.get().getId())
                    .productName(productDaoOptional.get().getProductName())
                    .denom(productDaoOptional.get().getDenom())
                    .category(productDaoOptional.get().getCategory())
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
