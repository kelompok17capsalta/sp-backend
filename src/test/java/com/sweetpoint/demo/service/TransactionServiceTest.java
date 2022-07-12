//package com.sweetpoint.demo.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//import java.util.*;
//
//
//import com.sweetpoint.demo.domain.dao.TransactionDao;
//
//import com.sweetpoint.demo.domain.dto.request.TransactionDto;
//import com.sweetpoint.demo.repository.TransactionRepository;
//import io.swagger.v3.oas.models.responses.ApiResponse;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//@SpringBootTest
//@Tag(value = "TransactionServiceTest")
//public class TransactionServiceTest {
//
//    @MockBean
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private TransactionService transactionService;
//
//    private final TransactionDao transaction = TransactionDao.builder()
//            .id(1L)
//            .descriptions("Transaksi")
//            .status("Sukses")
//            .points(500)
//            .userId(2L)
//            .category("Pulsa")
//            .credentials("081234567890")
//            .provider("Telkomsel")
//            .build();
//
//    // Create New Transaction Success Test (Kategori Pulsa)
//
//    @Test
//    void createNewTransaction_PulsaSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .userId(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Create New Transaction Success Test (Kategori E-Money)
//
//    @Test
//    void createNewTransaction_EMoneySuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(250)
//                .user(2L)
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(250)
//                .user(2L)
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(250, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("085209876543", data.getCredentials());
//        assertEquals("Gopay", data.getProvider());
//    }
//
//    // Create New Transaction Success Test (Kategori Cash Out)
//
//    @Test
//    void createNewTransaction_CashOutSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Sukses")
//                .points(700)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .status("Sukses")
//                .points(700)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(700, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("1166223388990", data.getCredentials());
//        assertEquals("BCA", data.getProvider());
//    }
//
//    // Create New Transaction Success Test (Kategori Paket Data)
//
//    @Test
//    void createNewTransaction_PaketDataSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Sukses")
//                .points(100)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .status("Sukses")
//                .points(100)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(100, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("085212345678", data.getCredentials());
//        assertEquals("IM3", data.getProvider());
//    }
//
//    //Create New Transaction Pending Test (Kategori Pulsa)
//
//    @Test
//    void createNewTransaction_PulsaPending_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Pending")
//                .points(500)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Pending")
//                .points(500)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Pending", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Create New Transaction Pending Test (Kategori E-Money)
//
//    @Test
//    void createNewTransaction_EMoneyPending_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Pending")
//                .points(250)
//                .user(2L)
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Pending")
//                .points(250)
//                .user(2L)
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Pending", data.getStatus());
//        assertEquals(250, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("085209876543", data.getCredentials());
//        assertEquals("Gopay", data.getProvider());
//    }
//
//    // Create New Transaction Pending Test (Kategori Cash Out)
//
//    @Test
//    void createNewTransaction_CashOutPending_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Pending")
//                .points(700)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .status("Pending")
//                .points(700)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Pending", data.getStatus());
//        assertEquals(700, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("1166223388990", data.getCredentials());
//        assertEquals("BCA", data.getProvider());
//    }
//
//    // Create New Transaction Pending Test (Kategori Paket Data)
//
//    @Test
//    void createNewTransaction_PaketDataPending_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Pending")
//                .points(100)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .status("Pending")
//                .points(100)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Pending", data.getStatus());
//        assertEquals(100, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("085212345678", data.getCredentials());
//        assertEquals("IM3", data.getProvider());
//    }
//
//    // Create New Transaction Failed Test (Kategori Pulsa)
//    @Test
//    public void  createNewTransaction_PulsaFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//
//    }
//
//    // Create New Transaction Failed Test (Kategori E-Money)
//    @Test
//    public void  createNewTransaction_EMoneyFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Failed")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Create New Transaction Failed Test (Kategori Cash Out)
//    @Test
//    public void  createNewTransaction_CashOutFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Failed")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Create New Transaction Failed Test (Kategori Paket Data)
//    @Test
//    public void  createNewTransaction_PaketDataFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Failed")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build()));
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Update Transaction Success Test (Kategori Pulsa)
//
//    @Test
//    public void updateTransaction_PulsaSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Update Transaction Success Test (Kategori E-Money)
//    @Test
//    public void updateTransaction_EMoneySuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Success")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Update Transaction Success Test (Kategori Cash Out)
//    @Test
//    public void  updateTransaction_CashOutSucces_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Success")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Update Transaction Success Test (Kategori Paket Data)
//    @Test
//    public void  updateTransaction_PaketDataSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Success")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Update Transaction Failed Test (Kategori Pulsa)
//    @Test
//    public void  updateTransaction_PulsaFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//
//    }
//
//    // Update Transaction Failed Test (Kategori E-Money)
//    @Test
//    public void  updateTransaction_EMoneyFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Failed")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Update Transaction Failed Test (Kategori Cash Out)
//    @Test
//    public void  updateTransaction_CashOutFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Failed")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Update Transaction Failed Test (Kategori Paket Data)
//    @Test
//    public void  updateTransaction_PaketDataFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Failed")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Delete Transaction Success Test
//
//    @Test
//    public void deleteTransactionSuccess_Test() {
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build()));
//        doNothing().when(transactionRepository).delete(any());
//
//        ResponseEntity<Object> transactions = transactionService.deleteTransaction(anyLong());
//
//        assertEquals(HttpStatus.OK.value(), transactions.getStatusCodeValue());
//
//    }
//
//    // Delete Transaction Failed Test
//
//    @Test
//    public void deleteTransactionFailed_Test() {
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty(TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build()));
//
//        ResponseEntity<Object> transactions = transactionService.deleteTransaction(anyLong());
//        assertEquals(HttpStatus.BAD_REQUEST.value(), transactions.getStatusCodeValue());
//
//
//
//    }
//
//    //Get All Transaction Success Test (Kategori Pulsa)
//
//    @Test
//    public void getAllTransaction_PulsaSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Get All Transaction Success Test (Kategori E-Money)
//    @Test
//    public void getAllTransaction_EMoneySuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Success")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get All Transaction Success Test (Kategori Cash Out)
//    @Test
//    public void  getAllTransaction_CashOutSucces_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Success")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get All Transaction Success Test (Kategori Paket Data)
//    @Test
//    public void  getAllTransaction_PaketDataSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Success")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Get All Transaction Failed Test (Kategori Pulsa)
//    @Test
//    public void  getAllTransaction_PulsaFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//
//    }
//
//    // Get All Transaction Failed Test (Kategori E-Money)
//    @Test
//    public void  getAllTransaction_EMoneyFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Failed")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get All Transaction Failed Test (Kategori Cash Out)
//    @Test
//    public void  getAllTransaction_CashOutFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Failed")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get All Transaction Failed Test (Kategori Paket Data)
//    @Test
//    public void  getAllTransaction_PaketDataFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Failed")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    //Get User Transaction Success Test (Kategori Pulsa)
//
//    @Test
//    public void getUserTransaction_PulsaSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Get User Transaction Success Test (Kategori E-Money)
//    @Test
//    public void getUserTransaction_EMoneySuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Success")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get User Transaction Success Test (Kategori Cash Out)
//    @Test
//    public void  getUserTransaction_CashOutSucces_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Success")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
////        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
////                .descriptions("Transaksi Cash Out")
////                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get User Transaction Success Test (Kategori Paket Data)
//    @Test
//    public void  getUSerTransaction_PaketDataSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Success")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction((1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Get User Transaction Failed Test (Kategori Pulsa)
//    @Test
//    public void  getUserTransaction_PulsaFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//
//    }
//
//    // Get User Transaction Failed Test (Kategori E-Money)
//    @Test
//    public void  getUserTransaction_EMoneyFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Failed")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get User Transaction Failed Test (Kategori Cash Out)
//    @Test
//    public void  getUserTransaction_CashOutFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Failed")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction((1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get User Transaction Failed Test (Kategori Paket Data)
//    @Test
//    public void  getUserTransaction_PaketDataFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Failed")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Get Transaction By Id Success Test (Kategori Pulsa)
//
//    @Test
//    public void getTransactionById_PulsaSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }
//
//    // Get Transaction By Id Success Test (Kategori E-Money)
//    @Test
//    public void getTransactionById_EMoneySuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Success")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get transactionById Success Test (Kategori Cash Out)
//    @Test
//    public void  getTransactionById_CashOutSucces_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Success")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get TransactionById Success Test (Kategori Paket Data)
//    @Test
//    public void  getTransactionById_PaketDataSuccess_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Success")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById((1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//    // Get TransactionById Failed Test (Kategori Pulsa)
//    @Test
//    public void  getTransactionById_PulsaFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Failed")
//                .points(300)
//                .user(2L)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//
//    }
//
//    // Get TransactionById Failed Test (Kategori E-Money)
//    @Test
//    public void  getTransactionById_EMoneyFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi E-Money")
//                .status("Failed")
//                .points(400)
//                .user(2L)
//                .category("E-Money")
//                .credentials("081234567890")
//                .provider("DANA")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi E-Money")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi E-Money", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(400, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("DANA", data.getProvider());
//
//    }
//
//    // Get TransactionById Failed Test (Kategori Cash Out)
//    @Test
//    public void  getTransactionById_CashOutFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Failed")
//                .points(550)
//                .user(2L)
//                .category("Cash Out")
//                .credentials("11223344556677889900")
//                .provider("BRI")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById((1L, TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(550, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("11223344556677889900", data.getCredentials());
//        assertEquals("BRI", data.getProvider());
//
//    }
//
//    // Get TransactionById Failed Test (Kategori Paket Data)
//    @Test
//    public void  getTransactionById_PaketDataFailed_Test() {
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Failed")
//                .points(500)
//                .user(2L)
//                .category("Paket Data")
//                .credentials("08967134567864")
//                .provider("TRI")
//                .build();
//
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L, TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .build());
//
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Failed", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(2L, data.getUser().getId());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("08967134567864", data.getCredentials());
//        assertEquals("TRI", data.getProvider());
//
//    }
//
//
//
//
//}