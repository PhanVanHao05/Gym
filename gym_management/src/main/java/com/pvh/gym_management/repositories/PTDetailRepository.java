package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.PTDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PTDetailRepository extends JpaRepository<PTDetail, Integer> {
    Optional<PTDetail> findByUserId(int userId);
}
