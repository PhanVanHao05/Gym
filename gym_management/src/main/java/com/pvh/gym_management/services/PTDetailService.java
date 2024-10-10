package com.pvh.gym_management.services;

import com.pvh.gym_management.pojo.PTDetail;

public interface PTDetailService {
    PTDetail createPTDetail(PTDetail ptDetail);
    PTDetail updatePTDetail(int id, PTDetail ptDetail);
    PTDetail getPTDetailById(int id);
    PTDetail getPTDetailByUserId(int userId);
}
