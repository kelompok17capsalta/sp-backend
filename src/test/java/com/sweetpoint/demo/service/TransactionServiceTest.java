package com.sweetpoint.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;


import com.sweetpoint.demo.domain.common.ApiResponse;
import com.sweetpoint.demo.domain.dao.TransactionDao;

import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.repository.TransactionRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Tag(value = "TransactionServiceTest")
public class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    private final TransactionDao transaction = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Pending")
            .points(500)
            .userId(2L)
            .category("Pulsa")
            .credentials("081234567890")
            .provider("Telkomsel")
            .productId(3L)
            .build();

    @Test
    void createNewTransaction_Success_Test() {
        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi")
                .status("Pending")
                .points(500)
                .userId(2L)
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .productId(3L)
                .build();

        when(transactionRepository.save(any())).thenReturn(transactionDao);
        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
                .descriptions("Transaksi")
                .status("Pending")
                .points(500)
                .userId(2L)
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .productId(3L)
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Transaksi", data.getDescriptions());
        assertEquals("Pending", data.getStatus());
        assertEquals(500, data.getPoints());
        assertEquals(2L, data.getUserId());
        assertEquals("Pulsa", data.getCategory());
        assertEquals("081234567890", data.getCredentials());
        assertEquals("Telkomsel", data.getProvider());
        assertEquals(3L, data.getProductId());
    }
}