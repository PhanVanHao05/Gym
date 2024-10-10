package com.pvh.gym_management.services;

import com.pvh.gym_management.dtos.PTCommentsRequestDTO;
import com.pvh.gym_management.dtos.PTCommentsResponseDTO;
import com.pvh.gym_management.pojo.PTComments;

import java.util.List;

public interface PTCommentsService {
    List<PTCommentsResponseDTO> getCommentsByPTId(int ptId);
    double getAverageRatingByPTId(int ptId);
    PTComments createComment(PTCommentsRequestDTO ptCommentsRequestDTO);
}
