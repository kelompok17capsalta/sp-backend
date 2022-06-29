package com.sweetpoint.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;


import com.sweetpoint.demo.domain.common.ApiResponse;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dto.ProductDto;
import com.sweetpoint.demo.repository.ProductRepository;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Tag(value = "ProductServiceTest")
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private final ProductDao product = ProductDao.builder()
            .id(1L)
            .productName("Nama")
            .denom(100000)
            .category("Cash Out")
            .descriptions("Test")
            .points(100)
            .stock(10)
            .image("test.jpeg")
            .build();

    @Test
    void createNewProduct_CashSuccess_Test() {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Cash Out")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.createNewProduct(ProductDto.builder()
                .productName("Nama")
                .denom(100000)
                .category("Cash Out")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Nama", data.getProductName());
        assertEquals(100000, data.getDenom());
        assertEquals("Cash Out", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    void createNewProduct_EMoneySuccess_Test() {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("E-Money")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.createNewProduct(ProductDto.builder()
                .productName("Nama")
                .denom(100000)
                .category("E-Money")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Nama", data.getProductName());
        assertEquals(100000, data.getDenom());
        assertEquals("E-Money", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    void createNewProduct_PulsaSuccess_Test() {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Pulsa")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.createNewProduct(ProductDto.builder()
                .productName("Nama")
                .denom(100000)
                .category("Pulsa")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Nama", data.getProductName());
        assertEquals(100000, data.getDenom());
        assertEquals("Pulsa", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    void createNewProduct_PaketDataSuccess_Test() {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.createNewProduct(ProductDto.builder()
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Nama", data.getProductName());
        assertEquals(100000, data.getDenom());
        assertEquals("Paket Data", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    public void getAllProduct_Success() {

        when(this.productRepository.findAll()).thenReturn(List.of(product));
        ResponseEntity<Object> responseEntity = productService.getAllProduct();
        ApiResponse<List<ProductDto>> apiResponse = (ApiResponse<List<ProductDto>>) responseEntity.getBody();
        List<ProductDto> productDtoList = apiResponse.getData();

        assertNotNull(productDtoList);
    }

    @Test
    public void getProductById_Success() {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productDao));
        ResponseEntity<Object> responseEntity = productService.getProductById(anyLong());

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void getProductById_NotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = productService.getProductById(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void updateProduct_Success() throws Exception {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(productDao));
        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.updateProduct(1L, ProductDto.builder()
                .productName("Produk")
                .denom(50000)
                .category("Cash Out")
                .descriptions("Deskripsi")
                .points(500)
                .stock(50)
                .image("image.jpeg")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals(50000, data.getDenom());
        assertEquals("Cash Out", data.getCategory());
        assertEquals("Deskripsi", data.getDescriptions());
        assertEquals(500, data.getPoints());
        assertEquals(50, data.getStock());
        assertEquals("image.jpeg", data.getImage());
    }

    @Test
    public void updateProduct_EMoney_Success() throws Exception {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(productDao));
        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.updateProduct(1L, ProductDto.builder()
                .category("E-Money")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals(100000, data.getDenom());
        assertEquals("E-Money", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    public void updateProduct_Pulsa_Success() throws Exception {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(productDao));
        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.updateProduct(1L, ProductDto.builder()
                .category("Pulsa")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals(100000, data.getDenom());
        assertEquals("Pulsa", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    public void updateProduct_PaketData_Success() throws Exception {
        ProductDao productDao = ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Pulsa")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(productDao));
        when(productRepository.save(any())).thenReturn(productDao);
        ResponseEntity<Object> responseEntity = productService.updateProduct(1L, ProductDto.builder()
                .category("Paket Data")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        ProductDto data = (ProductDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals(100000, data.getDenom());
        assertEquals("Paket Data", data.getCategory());
        assertEquals("Test", data.getDescriptions());
        assertEquals(100, data.getPoints());
        assertEquals(10, data.getStock());
        assertEquals("test.jpeg", data.getImage());
    }

    @Test
    public void updateProduct_Failed() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = productService.updateProduct(anyLong(), ProductDto.builder().build());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void deleteProduct_Success() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductDao.builder()
                .id(1L)
                .productName("Nama")
                .denom(100000)
                .category("Paket Data")
                .descriptions("Test")
                .points(100)
                .stock(10)
                .image("test.jpeg")
                .build()));
        ResponseEntity<Object> product = productService.deleteProduct(anyLong());

        assertEquals(HttpStatus.OK.value(), product.getStatusCodeValue());
    }

    @Test
    public void deleteProduct_Failed() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> product = productService.deleteProduct(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), product.getStatusCodeValue());
    }
}