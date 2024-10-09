package com.pvh.gym_management.controllers;

import com.pvh.gym_management.pojo.WorkSchedule;
import com.pvh.gym_management.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-schedules")
public class WorkScheduleController {

    @Autowired
    private WorkScheduleService workScheduleService;

    @PostMapping
    public ResponseEntity<WorkSchedule> createWorkSchedule(@RequestBody WorkSchedule workSchedule) {
        WorkSchedule createdSchedule = workScheduleService.createWorkSchedule(workSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkSchedule> getWorkScheduleById(@PathVariable int id) {
        WorkSchedule schedule = workScheduleService.getWorkScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/customer/{customerId}/pt/{ptId}")
    public ResponseEntity<List<WorkSchedule>> getWorkSchedulesByCustomerIdAndPtId(@PathVariable int customerId, @PathVariable int ptId) {
        List<WorkSchedule> schedules = workScheduleService.getWorkSchedulesByCustomerIdAndPtId(customerId, ptId);
        return ResponseEntity.ok(schedules);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkSchedule(@PathVariable int id) {
        workScheduleService.deleteWorkSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pt/{ptId}")
    public ResponseEntity<List<WorkSchedule>> getWorkSchedulesByPtId(@PathVariable int ptId) {
        List<WorkSchedule> schedules = workScheduleService.getWorkSchedulesByPtId(ptId);
        return ResponseEntity.ok(schedules);
    }
}
