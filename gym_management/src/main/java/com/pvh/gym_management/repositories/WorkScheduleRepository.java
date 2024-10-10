package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {
    List<WorkSchedule> findByCustomer_IdAndPt_Id(int customerId, int ptId);
    List<WorkSchedule> findByPt_Id(int ptId);
}
