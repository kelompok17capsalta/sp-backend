package com.sweetpoint.demo.repository;

import com.sweetpoint.demo.domain.dao.StoreDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreDao,Long> {
}
