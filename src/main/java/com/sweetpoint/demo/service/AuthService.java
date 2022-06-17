package com.sweetpoint.demo.service;

import com.sweetpoint.demo.domain.dto.DataResponse;
import com.sweetpoint.demo.security.JwtTokenProvider;
import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.TokenResponse;
import com.sweetpoint.demo.domain.dto.UserDto;
import com.sweetpoint.demo.repository.UserRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Object> register(UserDto req) {

        if (userRepository.existsByUsername(req.getUsername())) {
            log.error("Username is already registered");
            return ResponseUtil.build(ConstantApp.USERNAME_REGISTERED, null, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(req.getEmail())) {
            log.error("Email is already registered");
            return ResponseUtil.build(ConstantApp.EMAIL_REGISTERED, null, HttpStatus.BAD_REQUEST);
        }

        if (req.getRole().equals("Admin")) {
            UserDao userDao = new UserDao();
            userDao.setEmail(req.getEmail());
            userDao.setUsername(req.getUsername());
            userDao.setPassword(passwordEncoder.encode(req.getPassword()));
            userDao.setName(req.getName());
            userDao.setPoint(0);
            userDao.setRole("Admin");

            userRepository.save(userDao);
            return ResponseUtil.build(ConstantApp.KEY_FOUND, UserDto.builder()
                    .id(userDao.getId())
                    .email(userDao.getEmail())
                    .username(userDao.getUsername())
                    .name(userDao.getName())
                    .point(userDao.getPoint())
                    .role(userDao.getRole())
                    .build(), HttpStatus.OK);
        }

        if (req.getRole() == null || req.getRole().equals("User") || !req.getRole().equals("User")) {
            UserDao userDao = new UserDao();
            userDao.setEmail(req.getEmail());
            userDao.setUsername(req.getUsername());
            userDao.setPassword(passwordEncoder.encode(req.getPassword()));
            userDao.setName(req.getName());
            userDao.setPoint(0);
            userDao.setRole("User");

            userRepository.save(userDao);
            return ResponseUtil.build(ConstantApp.KEY_FOUND, UserDto.builder()
                    .id(userDao.getId())
                    .email(userDao.getEmail())
                    .username(userDao.getUsername())
                    .name(userDao.getName())
                    .point(userDao.getPoint())
                    .role(userDao.getRole())
                    .build(), HttpStatus.OK);
        }

        return null;
    }


    public ResponseEntity<?> authenticateAndGenerateToken(UserDto req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generationToken(authentication);
            return ResponseUtil.build(ConstantApp.KEY_FOUND, TokenResponse.builder().token(jwt).build(),
                    HttpStatus.OK);
        } catch (BadCredentialsException e){
            log.error("Bad Credential", e.getMessage());
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> generateData(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.substring(7);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setEmail(jwtTokenProvider.getEmail(token));
        dataResponse.setName(jwtTokenProvider.getName(token));
        dataResponse.setPoint(jwtTokenProvider.getPoint(token));
        return ResponseUtil.build(ConstantApp.KEY_FOUND, dataResponse, HttpStatus.OK);
    }
}
