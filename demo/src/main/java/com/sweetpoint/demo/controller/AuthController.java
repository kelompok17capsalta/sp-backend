package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.domain.dto.UserDto;
import com.sweetpoint.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDto req){
        return authService.register(req);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDto req){
        return authService.authenticateAndGenerateToken(req);
    }
}
