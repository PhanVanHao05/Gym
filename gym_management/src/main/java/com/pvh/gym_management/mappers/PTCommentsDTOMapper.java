package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.PTCommentsResponseDTO;
import com.pvh.gym_management.pojo.PTComments;
import org.springframework.stereotype.Component;

@Component
public class PTCommentsDTOMapper {

    public PTCommentsResponseDTO toResponseDTO(PTComments comment) {
        return PTCommentsResponseDTO.builder()
                .id(comment.getId())
                .customerId(comment.getCustomer().getId())
                .content(comment.getContent())
                .rating(comment.getRating())
                .createdDate(comment.getCreatedDate())
                .build();
    }
}
