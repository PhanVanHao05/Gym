package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.OnlinePaymentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlinePaymentResultRepository extends JpaRepository<OnlinePaymentResult, Long> {
}
