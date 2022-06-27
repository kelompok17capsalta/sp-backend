package com.sweetpoint.demo.controller;


import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.UserDao;
import com.sweetpoint.demo.domain.dto.PointsDto;
import com.sweetpoint.demo.service.PointsService;
import com.sweetpoint.demo.service.UserService;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping
public class PointsController {

    @Autowired
    private UserService userService;

    @Autowired
    private PointsService pointsService;

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return  pointsService.getALlPoints();
    }

   @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable Long id, @RequestBody PointsDto points) {
       UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());

       if (user.getRole().equals("Admin")) {
           return pointsService.updatePoints(id, points);
       }
       return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (Principal principal, @PathVariable Long id) {
        UserDao user = (UserDao) userService.loadUserByUsername(principal.getName());

        if (user.getRole().equals("Admin")) {
            return pointsService.deletePoints(id);
        }
        return ResponseUtil.build(ConstantApp.NOT_AUTHORIZED, null, HttpStatus.OK);
    }
}
