package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PTCommentsRequestDTO {
    private int scheduleId;
    private int customerId;
    private String content;
    private int rating;
}
