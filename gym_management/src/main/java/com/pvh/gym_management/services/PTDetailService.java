package com.pvh.gym_management.services;

import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.dtos.PTInfoDTO;
import java.util.Optional;

public interface PTDetailService {
    PTDetail createPTDetail(PTDetail ptDetail, int userId);

    Optional<PTDetail> updatePTDetail(int userId, PTDetail ptDetail);

    Optional<PTInfoDTO> getPTInfoById(int id);

    Optional<PTInfoDTO> getPTInfoByUserId(int userId);

    Optional<PTDetail> updateSalary(int userId, double salary);
}
