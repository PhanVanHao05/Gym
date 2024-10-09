package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.PTInfoDTO;
import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.services.PTDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pt")
public class PTDetailController {

    @Autowired
    private PTDetailService ptDetailService;

    //Tạo lương cho PT
    @PostMapping("/{userId}")
    public ResponseEntity<PTDetail> createPTDetail(@PathVariable int userId, @RequestBody PTDetail ptDetail) {
        PTDetail createdPTDetail = ptDetailService.createPTDetail(ptDetail, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPTDetail);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<PTDetail> updatePTDetail(@PathVariable int userId, @RequestBody PTDetail ptDetail) {
        Optional<PTDetail> updatedPTDetail = ptDetailService.updatePTDetail(userId, ptDetail);
        return updatedPTDetail
                .map(detail -> ResponseEntity.ok(detail))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Lấy thông tin PT kèm theo lương bằng id
    @GetMapping("/{id}")
    public ResponseEntity<PTInfoDTO> getPTInfoById(@PathVariable int id) {
        Optional<PTInfoDTO> ptInfo = ptDetailService.getPTInfoById(id);
        return ptInfo
                .map(info -> ResponseEntity.ok(info))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Lấy thông tin PT kèm theo lương bằng user_id
    @GetMapping("/user/{userId}")
    public ResponseEntity<PTInfoDTO> getPTInfoByUserId(@PathVariable int userId) {
        Optional<PTInfoDTO> ptInfo = ptDetailService.getPTInfoByUserId(userId);
        return ptInfo
                .map(info -> ResponseEntity.ok(info))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Cập nhật lương cho PT
    @PutMapping("/salary/{userId}")
    public ResponseEntity<PTDetail> updateSalary(@PathVariable int userId, @RequestParam double salary) {
        Optional<PTDetail> ptDetailOptional = ptDetailService.updateSalary(userId, salary);
        return ptDetailOptional
                .map(ptDetail -> ResponseEntity.ok(ptDetail))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
