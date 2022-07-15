package com.sweetpoint.demo.service;

import com.sweetpoint.demo.domain.common.ApiResponse;
import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.request.ProductDto;
import com.sweetpoint.demo.repository.ProductRepository;
import com.sweetpoint.demo.repository.TransactionRepository;
import com.sweetpoint.demo.repository.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Tag(value = "AdminServiceTest")
public class AdminServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private AdminService adminService;

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

    private final UserDao user = UserDao.builder()
            .id(1L)
            .email("user@gmail.com")
            .username("user")
            .password("password")
            .name("Seorang User")
            .address("Malang")
            .phone("081234567890")
            .point(0)
            .role("User")
            .build();

    private final TransactionDao transaction = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Sukses")
            .points(500)
            .user(UserDao.builder()
                    .id(1L)
                    .email("user@gmail.com")
                    .username("user")
                    .password("password")
                    .name("Seorang User")
                    .address("Malang")
                    .phone("081234567890")
                    .point(0)
                    .role("User")
                    .build())
            .category("Pulsa")
            .credentials("081234567890")
            .provider("Telkomsel")
            .build();

    @Test
    public void getAllData_Success() {
        when(this.productRepository.findAll()).thenReturn(List.of(product));
        when(this.userRepository.findAll()).thenReturn(List.of(user));
        when(this.transactionRepository.findAll()).thenReturn(List.of(transaction));
        ResponseEntity<Object> responseEntity = adminService.getAllData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void getAllData_Failed() {
        when(productRepository.findAll()).thenThrow(NullPointerException.class);
        when(userRepository.findAll()).thenThrow(NullPointerException.class);
        when(transactionRepository.findAll()).thenThrow(NullPointerException.class);
        ResponseEntity<Object> responseEntity = adminService.getAllData();
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }
}