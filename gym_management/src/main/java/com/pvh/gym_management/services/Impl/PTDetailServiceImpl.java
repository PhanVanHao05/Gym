package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.repositories.PTDetailRepository;
import com.pvh.gym_management.services.PTDetailService;
import com.pvh.gym_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTDetailServiceImpl implements PTDetailService {

    @Autowired
    private PTDetailRepository ptDetailRepository;

    @Autowired
    private UserService userService;

    @Override
    public PTDetail createPTDetail(PTDetail ptDetail) {
        return ptDetailRepository.save(ptDetail);
    }

    @Override
    public PTDetail updatePTDetail(int id, PTDetail ptDetail) {
        PTDetail existingPTDetail = ptDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PTDetail not found with id: " + id));

        existingPTDetail.setUser(ptDetail.getUser());
        existingPTDetail.setSalary(ptDetail.getSalary());

        return ptDetailRepository.save(existingPTDetail);
    }

    @Override
    public PTDetail getPTDetailById(int id) {
        return ptDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PTDetail not found with id: " + id));
    }

    @Override
    public PTDetail getPTDetailByUserId(int userId) {
        return ptDetailRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("PTDetail not found for userId: " + userId));
    }
}
