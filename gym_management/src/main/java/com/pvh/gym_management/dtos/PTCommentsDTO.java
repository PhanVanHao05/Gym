package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PTCommentsDTO {
    private int id;
    private int scheduleId;
    private int customerId;
    private String content;
    private int rating;
    private Timestamp createdDate;
}
