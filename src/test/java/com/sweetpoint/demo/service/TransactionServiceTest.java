package com.sweetpoint.demo.service;

import com.sweetpoint.demo.domain.common.ApiResponse;
import com.sweetpoint.demo.domain.dao.TransactionDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.request.TransactionDto;
import com.sweetpoint.demo.domain.dto.request.UserDto;
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
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@Tag(value = "TransactionServiceTest")
public class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    private final UserDao userDao = UserDao.builder()
            .id(1L)
            .email("sesa@gmail.com")
            .username("sesa11")
            .password("S3s4n14")
            .name("Sesania Yo")
            .address("Bogor")
            .phone("081234567890")
            .point(1000)
            .build();

    private final TransactionDao transactionDao = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Sukses")
            .points(500)
            .user(UserDao.builder().username("sesa11").build())
            .category("Pulsa")
            .credentials("081234567890")
            .provider("Telkomsel")
            .build();

    private final TransactionDao transactionEMoney = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Sukses")
            .points(500)
            .user(UserDao.builder().username("sesa11").build())
            .category("E - Money")
            .credentials("081234567890")
            .provider("DANA")
            .build();

    private final TransactionDao transactionCashOut = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Sukses")
            .points(500)
            .user(UserDao.builder().username("sesa11").build())
            .category("Cash Out ")
            .credentials("11223344556677889900")
            .provider("BRI")
            .build();

    private final TransactionDao transactionPaketData = TransactionDao.builder()
            .id(1L)
            .descriptions("Transaksi")
            .status("Sukses")
            .points(500)
            .user(UserDao.builder().username("sesa11").build())
            .category("Paket Data")
            .credentials("081234567890")
            .provider("Telkomsel")
            .build();


    // Create New Transaction Success Test (Kategori Pulsa)

//    @Test
//    void createNewTransaction_NonShoppingSuccess_Test() {
//        UserDao user = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        TransactionDto transactionDto = TransactionDto.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(userRepository.findByUsername(anyString())).thenReturn(userDao);
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(UserDto.builder()
//                        .username("sesa11")
//                        .build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(500, data.getPoints());
//        assertEquals(transactionDto.getUser().getUsername(), data.getUser().getUsername());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }

    // Create New Transaction Success Test (Kategori E-Money)

//    @Test
//    void createNewTransaction_EMoneySuccess_Test() {
//
//        UserDao user = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(250)
//                .user(UserDao.builder().username("sesa11").build())
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(250)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("E-Money")
//                .credentials("085209876543")
//                .provider("Gopay")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(250, data.getPoints());
//        assertEquals("sesa11", data.getUser().getUsername());
//        assertEquals("E-Money", data.getCategory());
//        assertEquals("085209876543", data.getCredentials());
//        assertEquals("Gopay", data.getProvider());
//    }

    // Create New Transaction Success Test (Kategori Cash Out)

//    @Test
//    void createNewTransaction_CashOutSuccess_Test() {
//
//        UserDao user = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Cash Out")
//                .status("Sukses")
//                .points(700)
//                .user(UserDao.builder().username("sesa11").build())
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
//                .descriptions("Transaksi Cash Out")
//                .status("Sukses")
//                .points(700)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Cash Out")
//                .credentials("1166223388990")
//                .provider("BCA")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Cash Out", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(700, data.getPoints());
//        assertEquals("sesa11", data.getUser().getUsername());
//        assertEquals("Cash Out", data.getCategory());
//        assertEquals("1166223388990", data.getCredentials());
//        assertEquals("BCA", data.getProvider());
//    }

    // Create New Transaction Success Test (Kategori Paket Data)

//    @Test
//    void createNewTransaction_PaketDataSuccess_Test() {
//
//        UserDao userDao = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Paket Data")
//                .status("Sukses")
//                .points(100)
//                .user(UserDao.builder().username("sesa11").build())
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build();
//
//        when(transactionRepository.save(any())).thenReturn(transactionDao);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
//                .descriptions("Transaksi Paket Data")
//                .status("Sukses")
//                .points(100)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Paket Data")
//                .credentials("085212345678")
//                .provider("IM3")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Paket Data", data.getDescriptions());
//        assertEquals("Sukses", data.getStatus());
//        assertEquals(100, data.getPoints());
//        assertEquals("sesa11", data.getUser().getUsername());
//        assertEquals("Paket Data", data.getCategory());
//        assertEquals("085212345678", data.getCredentials());
//        assertEquals("IM3", data.getProvider());
//    }

    // Create New Transaction InvalidData
    @Test
    void createNewProduct_InvalidData() {
        UserDao userDao = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi")
                .status("Sukses")
                .points(500)
                .user(UserDao.builder().username("sesa11").build())
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .build();

        when(transactionRepository.save(any())).thenReturn(transactionDao);
        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
                .id(1L)
                .descriptions("Transaksi")
                .status("Sukses")
                .points(500)
                .user(UserDto.builder().username("sesa11").build())
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    // Create New Transaction Failed
//    @Test
//    public void createNewTransactionFailed_Test() {
//        when(transactionRepository.findById(anyLong())).thenThrow(NullPointerException.class);
//        ResponseEntity<Object> responseEntity = transactionService.createNewTransaction(TransactionDto.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
//    }

//    @Test
//    public void getAllTransaction_Success() {
//
//        when(this.transactionRepository.findAll()).thenReturn(List.of(transactionDao));
//        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction();
//        ApiResponse<List<TransactionDto>> apiResponse = (ApiResponse<List<TransactionDto>>) responseEntity.getBody();
//        List<TransactionDto> transactionDtoList = apiResponse.getData();
//
//        assertNotNull(transactionDtoList);
//
//    }

    @Test
    public void getAllTransaction_Failed() {
        when(transactionRepository.findAll()).thenThrow(NullPointerException.class);
        ResponseEntity<Object> responseEntity = transactionService.getAllTransaction();
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }


    @Test
    public void getTransactionById_Success() {
        UserDao userDao = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi")
                .status("Sukses")
                .points(500)
                .user(UserDao.builder().username("sesa11").build())
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .build();
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transactionDao));
        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(anyLong());

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

    }

    @Test
    public void getTransactionById_NotFound() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void getTransactionById_Failed() {
        when(transactionRepository.findById(1L)).thenThrow(NullPointerException.class);
        ResponseEntity<Object> responseEntity = transactionService.getTransactionById(1L);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }

//    @Test
//    public void getUserTransaction_Success() {
//        UserDao userDao = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi")
//                .status("Sukses")
//                .points(500)
//                .user(UserDao.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transactionDao));
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(any());
//
//        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//
//    }

//    @Test
//    public void getUserTransaction_NotFound() {
//        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(any());
//
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//    }

//    @Test
//    public void getUserTransaction_Failed() {
//        when(transactionRepository.findById(1L)).thenThrow(NullPointerException.class);
//        ResponseEntity<Object> responseEntity = transactionService.getUserTransaction(any());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
//    }

    // Update Transaction Success Test (Kategori Pulsa)

//    @Test
//    public void updateTransaction_PulsaSuccess_Test() throws Exception {
//        UserDao user = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(UserDao.builder().username("sesa11").build())
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
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
//        assertEquals(1L, data.getId());
//        assertEquals("Transaksi Pulsa", data.getDescriptions());
//        assertEquals("Success", data.getStatus());
//        assertEquals(300, data.getPoints());
//        assertEquals("sesa11", data.getUser().getUsername());
//        assertEquals("Pulsa", data.getCategory());
//        assertEquals("081234567890", data.getCredentials());
//        assertEquals("Telkomsel", data.getProvider());
//    }

    // Update Transaction Success Test (Kategori E-Money)
    @Test
    public void updateTransaction_EMoneySuccess_Test() throws Exception {
        UserDao user = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi E-Money")
                .status("Success")
                .points(400)
                .user(UserDao.builder().username("sesa11").build())
                .category("E-Money")
                .credentials("081234567890")
                .provider("DANA")
                .build();

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
        when(transactionRepository.save(any())).thenReturn(transactionDao);
        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
                .descriptions("Transaksi E-Money")
                .build());

        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Transaksi E-Money", data.getDescriptions());
        assertEquals("Success", data.getStatus());
        assertEquals(400, data.getPoints());
        assertEquals("sesa11", data.getUser().getUsername());
        assertEquals("E-Money", data.getCategory());
        assertEquals("081234567890", data.getCredentials());
        assertEquals("DANA", data.getProvider());

    }

    // Update Transaction Success Test (Kategori Cash Out)
    @Test
    public void updateTransaction_CashOutSucces_Test() throws Exception {
        UserDao user = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi Cash Out")
                .status("Success")
                .points(550)
                .user(UserDao.builder().username("sesa11").build())
                .category("Cash Out")
                .credentials("11223344556677889900")
                .provider("BRI")
                .build();

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
        when(transactionRepository.save(any())).thenReturn(transactionDao);
        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
                .descriptions("Transaksi Cash Out")
                .build());

        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Transaksi Cash Out", data.getDescriptions());
        assertEquals("Success", data.getStatus());
        assertEquals(550, data.getPoints());
        assertEquals("sesa11", data.getUser().getUsername());
        assertEquals("Cash Out", data.getCategory());
        assertEquals("11223344556677889900", data.getCredentials());
        assertEquals("BRI", data.getProvider());

    }

    // Update Transaction Success Test (Kategori Paket Data)
    @Test
    public void updateTransaction_PaketDataSuccess_Test() throws Exception {
        UserDao user = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        TransactionDao transactionDao = TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi Paket Data")
                .status("Success")
                .points(500)
                .user(UserDao.builder().username("sesa11").build())
                .category("Paket Data")
                .credentials("08967134567864")
                .provider("TRI")
                .build();

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
        when(transactionRepository.save(any())).thenReturn(transactionDao);
        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
                .descriptions("Transaksi Paket Data")
                .build());

        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        TransactionDto data = (TransactionDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("Transaksi Paket Data", data.getDescriptions());
        assertEquals("Success", data.getStatus());
        assertEquals(500, data.getPoints());
        assertEquals("sesa11", data.getUser().getUsername());
        assertEquals("Paket Data", data.getCategory());
        assertEquals("08967134567864", data.getCredentials());
        assertEquals("TRI", data.getProvider());

    }

    @Test
    public void updateTransaction_Failed() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(anyLong(), TransactionDto.builder().build());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());

    }

//    @Test
//    public void updateTransaction_Failed_Error() throws Exception {
//        UserDao user = UserDao.builder()
//                .id(1L)
//                .email("sesa@gmail.com")
//                .username("sesa11")
//                .password("S3s4n14")
//                .name("Sesania Yo")
//                .address("Bogor")
//                .phone("081234567890")
//                .point(1000)
//                .build();
//
//        TransactionDao transactionDao = TransactionDao.builder()
//                .id(1L)
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(UserDao.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build();
//
//        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionDao));
//        when(transactionRepository.save(any())).thenThrow(NullPointerException.class);
//        ResponseEntity<Object> responseEntity = transactionService.updateTransaction(1L, TransactionDto.builder()
//                .descriptions("Transaksi Pulsa")
//                .status("Success")
//                .points(300)
//                .user(UserDto.builder().username("sesa11").build())
//                .category("Pulsa")
//                .credentials("081234567890")
//                .provider("Telkomsel")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
//
//
//    }

    // Delete Transaction Success Test

    @Test
    public void deleteTransactionSuccess_Test() {
        UserDao user = UserDao.builder()
                .id(1L)
                .email("sesa@gmail.com")
                .username("sesa11")
                .password("S3s4n14")
                .name("Sesania Yo")
                .address("Bogor")
                .phone("081234567890")
                .point(1000)
                .build();

        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(TransactionDao.builder()
                .id(1L)
                .descriptions("Transaksi")
                .status("Sukses")
                .points(500)
                .user(UserDao.builder().username("sesa11").build())
                .category("Pulsa")
                .credentials("081234567890")
                .provider("Telkomsel")
                .build()));
        ResponseEntity<Object> transactions = transactionService.deleteTransaction(anyLong());

        assertEquals(HttpStatus.OK.value(), transactions.getStatusCodeValue());

    }

    // Delete Transaction Failed Test

    @Test
    public void deleteTransactionFailed_Test() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> transactions = transactionService.deleteTransaction(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), transactions.getStatusCodeValue());

    }

    //Delete Transaction Failed Error Test
    @Test
    public void deleteTransactionFailedError_Test() {
        when(transactionRepository.findById(anyLong())).thenThrow(NullPointerException.class);
        doNothing().when(transactionRepository).delete(any());

        ResponseEntity<Object> responseEntity = transactionService.deleteTransaction(anyLong());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }


}