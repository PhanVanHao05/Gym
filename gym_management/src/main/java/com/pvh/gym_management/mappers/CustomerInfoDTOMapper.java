package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.CustomerInfoDTO;
import com.pvh.gym_management.pojo.CustomerDetail;
import com.pvh.gym_management.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoDTOMapper {

    public CustomerInfoDTO toCustomerInfoDTO(CustomerDetail customerDetail) {
        User user = customerDetail.getUser();

        return CustomerInfoDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .isActive(user.isActive())
                .weight(customerDetail.getWeight())
                .height(customerDetail.getHeight())
                .build();
    }

    public CustomerDetail toCustomerDetail(CustomerInfoDTO customerInfoDTO, User user) {
        return new CustomerDetail(
                user.getId(),
                user,
                customerInfoDTO.getWeight(),
                customerInfoDTO.getHeight()
        );
    }
}
