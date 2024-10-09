package com.pvh.gym_management.services;

import com.pvh.gym_management.pojo.WorkSchedule;

import java.util.List;

public interface WorkScheduleService {
    WorkSchedule createWorkSchedule(WorkSchedule workSchedule);
    WorkSchedule getWorkScheduleById(int id);
    List<WorkSchedule> getWorkSchedulesByCustomerIdAndPtId(int customerId, int ptId);
    void deleteWorkSchedule(int id);
    List<WorkSchedule> getWorkSchedulesByPtId(int ptId);
}

