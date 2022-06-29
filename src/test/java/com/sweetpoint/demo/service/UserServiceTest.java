package com.sweetpoint.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;


import com.sweetpoint.demo.domain.common.ApiResponse;

import com.sweetpoint.demo.domain.dao.ProductDao;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.ProductDto;
import com.sweetpoint.demo.domain.dto.UserDto;
import com.sweetpoint.demo.repository.UserRepository;
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
@Tag(value = "UserServiceTest")
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @Test
    public void getAllUser_Success() {

        when(this.userRepository.findAll()).thenReturn(List.of(user));
        ResponseEntity<Object> responseEntity = userService.getAllUser();
        ApiResponse<List<UserDto>> apiResponse = (ApiResponse<List<UserDto>>) responseEntity.getBody();
        List<UserDto> userDtoList = apiResponse.getData();

        assertNotNull(userDtoList);
    }

    @Test
    public void getUserById_Success() {
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
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userDao));
        ResponseEntity<Object> responseEntity = userService.getUserById(anyLong());

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void getUserById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = userService.getUserById(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void updateUser_Success() throws Exception {
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

        when(userRepository.findById(1L)).thenReturn(Optional.of(userDao));
        when(userRepository.save(any())).thenReturn(userDao);
        ResponseEntity<Object> responseEntity = userService.updateUser(1L, UserDto.builder()
                .email("test@gmail.com")
                .username("test")
                .name("Test")
                .address("Test")
                .phone("081234567891")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        UserDto data = (UserDto) Objects.requireNonNull(apiResponse).getData();
        assertEquals(1L, data.getId());
        assertEquals("test@gmail.com", data.getEmail());
        assertEquals("test", data.getUsername());
        assertEquals("Test", data.getName());
        assertEquals("Test", data.getAddress());
        assertEquals("081234567891", data.getPhone());
    }

    @Test
    public void updateUser_Failed() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> responseEntity = userService.updateUser(anyLong(), UserDto.builder().build());

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void deleteUser_Success() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserDao.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("password")
                .name("Seorang User")
                .address("Malang")
                .phone("081234567890")
                .point(0)
                .role("User")
                .build()));
        ResponseEntity<Object> user = userService.deleteUser(anyLong());

        assertEquals(HttpStatus.OK.value(), user.getStatusCodeValue());
    }

    @Test
    public void deleteUser_Failed() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<Object> user = userService.deleteUser(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST.value(), user.getStatusCodeValue());
    }
}