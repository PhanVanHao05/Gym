package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.pojo.WorkSchedule;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.repositories.WorkScheduleRepository;
import com.pvh.gym_management.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WorkSchedule createWorkSchedule(WorkSchedule workSchedule) {
        User pt = userRepository.findById(workSchedule.getPt().getId())
                .orElseThrow(() -> new IllegalArgumentException("PT không tồn tại với id: " + workSchedule.getPt().getId()));

        User customer = userRepository.findById(workSchedule.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer không tồn tại với id: " + workSchedule.getCustomer().getId()));

        workSchedule.setPt(pt);
        workSchedule.setCustomer(customer);

        return workScheduleRepository.save(workSchedule);
    }


    @Override
    public WorkSchedule getWorkScheduleById(int id) {
        return workScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work schedule not found"));
    }

    @Override
    public List<WorkSchedule> getWorkSchedulesByCustomerIdAndPtId(int customerId, int ptId) {
        return workScheduleRepository.findByCustomer_IdAndPt_Id(customerId, ptId);
    }

    @Override
    public void deleteWorkSchedule(int id) {
        workScheduleRepository.deleteById(id);
    }

    @Override
    public List<WorkSchedule> getWorkSchedulesByPtId(int ptId) {
        return workScheduleRepository.findByPt_Id(ptId);
    }
}
