package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.PTDetailDTO;
import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.services.PTDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pt-details")
public class PTDetailController {

    @Autowired
    private PTDetailService ptDetailService;


    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<PTDetail> createPTDetail(@RequestBody PTDetailDTO ptDetailDTO) {
        User user = userRepository.findById(ptDetailDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ptDetailDTO.getUserId()));

        PTDetail ptDetail = new PTDetail();
        ptDetail.setUser(user);
        ptDetail.setSalary(ptDetailDTO.getSalary());

        PTDetail createdPTDetail = ptDetailService.createPTDetail(ptDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPTDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PTDetail> updatePTDetail(@PathVariable int id, @RequestBody PTDetailDTO ptDetailDTO) {
        PTDetail existingPTDetail = ptDetailService.getPTDetailById(id);
        if (existingPTDetail == null) {
            throw new RuntimeException("PTDetail not found with id: " + id);
        }

        existingPTDetail.setSalary(ptDetailDTO.getSalary());

        PTDetail updatedPTDetail = ptDetailService.updatePTDetail(id, existingPTDetail);
        return ResponseEntity.ok(updatedPTDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PTDetail> getPTDetailById(@PathVariable int id) {
        PTDetail ptDetail = ptDetailService.getPTDetailById(id);
        return ResponseEntity.ok(ptDetail);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PTDetail> getPTDetailByUserId(@PathVariable int userId) {
        PTDetail ptDetail = ptDetailService.getPTDetailByUserId(userId);
        return ResponseEntity.ok(ptDetail);
    }
}
