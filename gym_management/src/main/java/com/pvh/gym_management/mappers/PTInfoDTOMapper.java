package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.PTInfoDTO;
import com.pvh.gym_management.pojo.PTDetail;
import org.springframework.stereotype.Component;

@Component
public class PTInfoDTOMapper {

    public PTInfoDTO toPTInfoDTO(PTDetail ptDetail) {
        if (ptDetail == null || ptDetail.getUser() == null) {
            return null;
        }

        return PTInfoDTO.builder()
                .id(ptDetail.getUser().getId())
                .firstName(ptDetail.getUser().getFirstName())
                .lastName(ptDetail.getUser().getLastName())
                .email(ptDetail.getUser().getEmail())
                .phone(ptDetail.getUser().getPhone())
                .address(ptDetail.getUser().getAddress())
                .avatar(ptDetail.getUser().getAvatar())
                .isActive(ptDetail.getUser().isActive())
                .salary(ptDetail.getSalary())
                .build();
    }
}
