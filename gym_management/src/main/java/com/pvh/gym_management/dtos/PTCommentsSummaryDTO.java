package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PTCommentsSummaryDTO {
    private List<PTCommentsResponseDTO> comments;
    private double averageRating;
}
