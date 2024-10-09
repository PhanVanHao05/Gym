package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.UserDTO;
import com.pvh.gym_management.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .isActive(user.isActive())
                .avatar(user.getAvatar())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }
}
