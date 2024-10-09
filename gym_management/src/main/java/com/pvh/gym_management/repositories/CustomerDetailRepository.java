package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer> {
    Optional<CustomerDetail> findByUserId(int userId);
}
