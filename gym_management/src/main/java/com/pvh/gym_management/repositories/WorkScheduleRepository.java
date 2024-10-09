package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {

    List<WorkSchedule> findByCustomer_IdAndPt_UserId(int customerId, int ptId);
    List<WorkSchedule> findByPt_UserId(int ptId);
}
