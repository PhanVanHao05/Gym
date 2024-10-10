package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.PTCommentsRequestDTO;
import com.pvh.gym_management.dtos.PTCommentsResponseDTO;
import com.pvh.gym_management.mappers.PTCommentsDTOMapper;
import com.pvh.gym_management.pojo.PTComments;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.pojo.WorkSchedule;
import com.pvh.gym_management.repositories.PTCommentsRepository;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.repositories.WorkScheduleRepository;
import com.pvh.gym_management.services.PTCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PTCommentsServiceImpl implements PTCommentsService {

    @Autowired
    private PTCommentsRepository ptCommentsRepository;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PTCommentsDTOMapper ptCommentsDTOMapper;

    @Override
    public List<PTCommentsResponseDTO> getCommentsByPTId(int ptId) {
        List<PTComments> comments = ptCommentsRepository.findBySchedule_Pt_Id(ptId);
        return comments.stream()
                .map(ptCommentsDTOMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageRatingByPTId(int ptId) {
        List<PTComments> comments = ptCommentsRepository.findBySchedule_Pt_Id(ptId);
        return comments.stream()
                .mapToInt(PTComments::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public PTComments createComment(PTCommentsRequestDTO ptCommentsRequestDTO) {
        WorkSchedule schedule = workScheduleRepository.findById(ptCommentsRequestDTO.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + ptCommentsRequestDTO.getScheduleId()));

        User customer = userRepository.findById(ptCommentsRequestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + ptCommentsRequestDTO.getCustomerId()));

        PTComments ptComments = new PTComments();
        ptComments.setSchedule(schedule);
        ptComments.setCustomer(customer);
        ptComments.setContent(ptCommentsRequestDTO.getContent());
        ptComments.setRating(ptCommentsRequestDTO.getRating());

        return ptCommentsRepository.save(ptComments);
    }
}
