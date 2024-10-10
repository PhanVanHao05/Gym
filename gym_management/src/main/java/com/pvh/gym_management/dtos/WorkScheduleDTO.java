package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkScheduleDTO {
    private Integer id;
    private UserDTO pt;
    private UserDTO customer;
    private LocalDate workDay;
    private LocalTime startTime;
    private LocalTime endTime;
}
