package com.pvh.gym_management.services;

import com.pvh.gym_management.pojo.CustomerDetail;
import com.pvh.gym_management.dtos.CustomerInfoDTO;
import java.util.Optional;

public interface CustomerDetailService {
    CustomerDetail createCustomerDetail(CustomerDetail customerDetail, int userId);
    Optional<CustomerDetail> updateCustomerDetail(int userId, CustomerDetail customerDetail);
    Optional<CustomerInfoDTO> getCustomerInfoById(int userId);
    Optional<CustomerInfoDTO> getCustomerInfoByUserId(int userId);
    Optional<CustomerDetail> updateBodyStats(int userId, double height, double weight);
}
