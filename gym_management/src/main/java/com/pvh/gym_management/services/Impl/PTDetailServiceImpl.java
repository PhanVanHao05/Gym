package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.PTInfoDTO;
import com.pvh.gym_management.mappers.PTInfoDTOMapper;
import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.repositories.PTDetailRepository;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.services.PTDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PTDetailServiceImpl implements PTDetailService {

    @Autowired
    private PTDetailRepository ptDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PTInfoDTOMapper ptInfoDTOMapper;

    @Override
    public PTDetail createPTDetail(PTDetail ptDetail, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        ptDetail.setUser(user);

        return ptDetailRepository.save(ptDetail);
    }

    @Override
    public Optional<PTDetail> updatePTDetail(int userId, PTDetail ptDetailUpdates) {
        Optional<PTDetail> existingPTDetailOpt = ptDetailRepository.findByUserId(userId);

        if (existingPTDetailOpt.isPresent()) {
            PTDetail existingPTDetail = existingPTDetailOpt.get();
            existingPTDetail.setSalary(ptDetailUpdates.getSalary());

            ptDetailRepository.save(existingPTDetail);
            return Optional.of(existingPTDetail);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PTInfoDTO> getPTInfoById(int id) {
        return ptDetailRepository.findById(id)
                .map(ptInfoDTOMapper::toPTInfoDTO);
    }

    @Override
    public Optional<PTInfoDTO> getPTInfoByUserId(int userId) {
        return ptDetailRepository.findByUserId(userId)
                .map(ptInfoDTOMapper::toPTInfoDTO);
    }

    @Override
    public Optional<PTDetail> updateSalary(int userId, double salary) {
        Optional<PTDetail> ptDetailOptional = ptDetailRepository.findByUserId(userId);
        if (ptDetailOptional.isPresent()) {
            PTDetail ptDetail = ptDetailOptional.get();
            ptDetail.setSalary(salary);
            ptDetailRepository.save(ptDetail);
            return Optional.of(ptDetail);
        }
        return Optional.empty();
    }

}
