package com.sweetpoint.demo.repository;


import com.sweetpoint.demo.domain.dao.PointsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<PointsDao, Long> {
}
