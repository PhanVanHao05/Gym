package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.UserPublicDTO;
import com.pvh.gym_management.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserPublicDTOMapper {

    public UserPublicDTO toUserPublicDTO(User user) {
        return UserPublicDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .isActive(user.isActive())
                .build();
    }
}
