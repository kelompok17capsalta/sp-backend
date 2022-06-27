package com.sweetpoint.demo.service;


import com.sweetpoint.demo.constant.ConstantApp;
import com.sweetpoint.demo.domain.dao.PointsDao;
import com.sweetpoint.demo.domain.dto.PointsDto;
import com.sweetpoint.demo.repository.PointsRepository;
import com.sweetpoint.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class PointsService {

    @Autowired
    private PointsRepository pointsRepository;

    public ResponseEntity<Object> getALlPoints() {
        try {
            log.info("Getting all points");
            List<PointsDao> pointsDaoList = pointsRepository.findAll();
            List<PointsDto> pointsDtoList = new ArrayList<>();

            for (PointsDao pointsDao: pointsDaoList) {
                pointsDtoList.add(PointsDto.builder()
                        .id(pointsDao.getId())
                        .pointAmount(pointsDao.getPointAmount())
                        .build());
            }

            return ResponseUtil.build(ConstantApp.KEY_FOUND, pointsDtoList, HttpStatus.OK);
        }catch (Exception e){
            log.error("Got an error when getting all points, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updatePoints(Long id, PointsDto request) {
        try {
            log.info("Updating points with id : {}", id);
            Optional<PointsDao> pointsDaoOptional = pointsRepository.findById(id);
            if (pointsDaoOptional.isEmpty()) {
                log.info("Points Not Found!");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            PointsDao pointsDao = pointsDaoOptional.get();
            pointsDao.setPointAmount(request.getPointAmount());
            pointsRepository.save(pointsDao);

            return ResponseUtil.build(ConstantApp.KEY_FOUND, PointsDto.builder()
                    .id(pointsDao.getId())
                    .pointAmount(pointsDao.getPointAmount())
                    .build(), HttpStatus.OK);

        } catch (Exception e) {
            log.error("Got an error when updating points, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> deletePoints(Long id) {
        try {
            log.info("Deleting points : {} ", id);
            Optional<PointsDao> pointsDaoOptional = pointsRepository.findById(id);

            if (pointsDaoOptional.isEmpty()) {
                log.info("Points not found");
                return ResponseUtil.build(ConstantApp.KEY_NOT_FOUND, null, HttpStatus.BAD_REQUEST);

            }
            pointsRepository.delete(pointsDaoOptional.get());
            return ResponseUtil.build(ConstantApp.KEY_FOUND, null, HttpStatus.OK);

        }catch (Exception e) {
            log.error("Got an error when deleting points, error : {}", e.getMessage());
            return ResponseUtil.build(ConstantApp.ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
