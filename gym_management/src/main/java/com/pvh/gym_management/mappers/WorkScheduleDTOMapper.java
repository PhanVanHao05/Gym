package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.UserDTO;
import com.pvh.gym_management.dtos.WorkScheduleDTO;
import com.pvh.gym_management.pojo.WorkSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkScheduleDTOMapper {

    @Autowired
    private UserDTOMapper userDTOMapper;

    public WorkScheduleDTO toWorkScheduleDTO(WorkSchedule workSchedule) {
        if (workSchedule == null) {
            return null;
        }

        UserDTO ptDTO = userDTOMapper.toUserDTO(workSchedule.getPt());
        UserDTO customerDTO = userDTOMapper.toUserDTO(workSchedule.getCustomer());

        return WorkScheduleDTO.builder()
                .id(workSchedule.getId())
                .pt(ptDTO)
                .customer(customerDTO)
                .workDay(workSchedule.getWorkDay())
                .startTime(workSchedule.getStartTime())
                .endTime(workSchedule.getEndTime())
                .build();
    }
}
