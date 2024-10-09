package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.CustomerInfoDTO;
import com.pvh.gym_management.mappers.CustomerInfoDTOMapper;
import com.pvh.gym_management.pojo.CustomerDetail;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.repositories.CustomerDetailRepository;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.services.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private CustomerInfoDTOMapper customerInfoDTOMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerDetail createCustomerDetail(CustomerDetail customerDetail, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        customerDetail.setUser(user);
        return customerDetailRepository.save(customerDetail);
    }

    @Override
    public Optional<CustomerDetail> updateCustomerDetail(int userId, CustomerDetail customerDetailDetails) {
        return customerDetailRepository.findById(userId)
                .map(customerDetail -> {
                    customerDetail.setWeight(customerDetailDetails.getWeight());
                    customerDetail.setHeight(customerDetailDetails.getHeight());
                    return customerDetailRepository.save(customerDetail);
                });
    }

    @Override
    public Optional<CustomerInfoDTO> getCustomerInfoById(int userId) {
        return customerDetailRepository.findById(userId)
                .map(customerInfoDTOMapper::toCustomerInfoDTO);
    }

    @Override
    public Optional<CustomerInfoDTO> getCustomerInfoByUserId(int userId) {
        return customerDetailRepository.findByUserId(userId)
                .map(customerInfoDTOMapper::toCustomerInfoDTO);
    }

    @Override
    public Optional<CustomerDetail> updateBodyStats(int userId, double height, double weight) {
        Optional<CustomerDetail> customerDetailOptional = customerDetailRepository.findByUserId(userId);
        if (customerDetailOptional.isPresent()) {
            CustomerDetail customerDetail = customerDetailOptional.get();
            customerDetail.setHeight(height);
            customerDetail.setWeight(weight);
            customerDetailRepository.save(customerDetail);
            return Optional.of(customerDetail);
        }
        return Optional.empty();
    }
}
