package com.sweetpoint.demo.repository;

import com.sweetpoint.demo.domain.dao.TransactionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {
}
