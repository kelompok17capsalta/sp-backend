package com.sweetpoint.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;


import com.sweetpoint.demo.domain.common.ApiResponse;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.response.TokenResponse;
import com.sweetpoint.demo.domain.dto.request.UserDto;
import com.sweetpoint.demo.repository.UserRepository;
import com.sweetpoint.demo.security.JwtTokenProvider;

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
@Tag(value = "AuthServiceTest")
public class AuthServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthService authService;

    @Test
    public void registerSuccess_Test() throws Exception {

        UserDao userDao = UserDao.builder()
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

        when(userRepository.existsByUsername(userDao.getUsername())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(userDao);

        ResponseEntity<Object> responseEntity = authService.register(UserDto.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .name("Seorang User")
                .address("Malang")
                .phone("081234567890")
                .point(0)
                .role("User")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        UserDto data = (UserDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void registerFail_UsernameExists_Test() throws Exception {

        UserDao userDao = UserDao.builder()
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

        when(userRepository.existsByUsername(userDao.getUsername())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(userDao);

        ResponseEntity<Object> responseEntity = authService.register(UserDto.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .name("Seorang User")
                .address("Malang")
                .phone("081234567890")
                .point(0)
                .role("User")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        UserDto data = (UserDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void registerFail_EmailExists_Test() throws Exception {

        UserDao userDao = UserDao.builder()
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

        when(userRepository.existsByEmail(userDao.getEmail())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(userDao);

        ResponseEntity<Object> responseEntity = authService.register(UserDto.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .name("Seorang User")
                .address("Malang")
                .phone("081234567890")
                .point(0)
                .role("User")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        UserDto data = (UserDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void authenticateAndGenerateToken_Success_Test() throws Exception {
        when(jwtTokenProvider.generationToken(any())).thenReturn("TOKEN");

        ResponseEntity<?> responseEntity = authService.authenticateAndGenerateToken(UserDto.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        TokenResponse tokenResponse = (TokenResponse) Objects.requireNonNull(response).getData();
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("TOKEN", tokenResponse.getToken());
    }

//    @Test
//    public void authenticateAndGenerateToken_Fail_Test() throws Exception {
//        UserDao userDao = UserDao.builder()
//                .id(1L)
//                .email("user@gmail.com")
//                .username("user")
//                .password("password")
//                .name("Seorang User")
//                .address("Malang")
//                .phone("081234567890")
//                .point(0)
//                .role("User")
//                .build();
//
//        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(userDao));
//
//        ResponseEntity<?> responseEntity = authService.authenticateAndGenerateToken(UserDto.builder()
//                .username("123")
//                .password("bb123123bb")
//                .build());
//        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//    }
}