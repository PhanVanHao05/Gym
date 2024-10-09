package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.UserUpdateDTO;
import com.pvh.gym_management.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateDTOMapper {
    public void updateUserFromDto(UserUpdateDTO UserUpdateDTO, User user) {
        if (UserUpdateDTO.getFirstName() != null) {
            user.setFirstName(UserUpdateDTO.getFirstName());
        }
        if (UserUpdateDTO.getLastName() != null) {
            user.setLastName(UserUpdateDTO.getLastName());
        }
        if (UserUpdateDTO.getPhone() != null) {
            user.setPhone(UserUpdateDTO.getPhone());
        }
        if (UserUpdateDTO.getAvatar() != null) {
            user.setAvatar(UserUpdateDTO.getAvatar());
        }
        if (UserUpdateDTO.getAddress() != null) {
            user.setAddress(UserUpdateDTO.getAddress());
        }
    }
}
