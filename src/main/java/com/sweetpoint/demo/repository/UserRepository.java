package com.sweetpoint.demo.repository;

import com.sweetpoint.demo.domain.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao,Long> {
    UserDao getDistinctTopByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
