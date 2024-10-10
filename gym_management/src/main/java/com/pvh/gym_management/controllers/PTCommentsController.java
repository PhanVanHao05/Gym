package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.PTCommentsResponseDTO;
import com.pvh.gym_management.dtos.PTCommentsRequestDTO;
import com.pvh.gym_management.services.PTCommentsService;
import com.pvh.gym_management.pojo.PTComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pt-comments")
public class PTCommentsController {

    @Autowired
    private PTCommentsService ptCommentsService;

    @GetMapping("/pt/{ptId}")
    public ResponseEntity<?> getCommentsByPTId(@PathVariable int ptId) {
        List<PTCommentsResponseDTO> comments = ptCommentsService.getCommentsByPTId(ptId);

        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No comments found for PT with id: " + ptId);
        }

        double averageRating = ptCommentsService.getAverageRatingByPTId(ptId);

        return ResponseEntity.ok(new PTCommentsWithRatingResponse(comments, averageRating));
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody PTCommentsRequestDTO requestDTO) {
        PTComments createdComment = ptCommentsService.createComment(requestDTO);

        if (createdComment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create comment");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    private static class PTCommentsWithRatingResponse {
        private List<PTCommentsResponseDTO> comments;
        private double averageRating;

        public PTCommentsWithRatingResponse(List<PTCommentsResponseDTO> comments, double averageRating) {
            this.comments = comments;
            this.averageRating = averageRating;
        }

        public List<PTCommentsResponseDTO> getComments() {
            return comments;
        }

        public double getAverageRating() {
            return averageRating;
        }
    }
}
