package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.WorkScheduleDTO;
import com.pvh.gym_management.mappers.WorkScheduleDTOMapper;
import com.pvh.gym_management.pojo.WorkSchedule;
import com.pvh.gym_management.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/work-schedules")
public class WorkScheduleController {

    @Autowired
    private WorkScheduleService workScheduleService;

    @Autowired
    private WorkScheduleDTOMapper workScheduleDTOMapper;

    @PostMapping
    public ResponseEntity<WorkScheduleDTO> createWorkSchedule(@RequestBody WorkSchedule workSchedule) {
        WorkSchedule createdSchedule = workScheduleService.createWorkSchedule(workSchedule);
        WorkScheduleDTO scheduleDTO = workScheduleDTOMapper.toWorkScheduleDTO(createdSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkScheduleDTO> getWorkScheduleById(@PathVariable int id) {
        WorkSchedule schedule = workScheduleService.getWorkScheduleById(id);
        WorkScheduleDTO scheduleDTO = workScheduleDTOMapper.toWorkScheduleDTO(schedule);
        return ResponseEntity.ok(scheduleDTO);
    }

    @GetMapping("/customer/{customerId}/pt/{ptId}")
    public ResponseEntity<List<WorkScheduleDTO>> getWorkSchedulesByCustomerIdAndPtId(
            @PathVariable int customerId, @PathVariable int ptId) {
        List<WorkSchedule> schedules = workScheduleService.getWorkSchedulesByCustomerIdAndPtId(customerId, ptId);
        List<WorkScheduleDTO> scheduleDTOs = schedules.stream()
                .map(workScheduleDTOMapper::toWorkScheduleDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkSchedule(@PathVariable int id) {
        workScheduleService.deleteWorkSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pt/{ptId}")
    public ResponseEntity<List<WorkScheduleDTO>> getWorkSchedulesByPtId(@PathVariable int ptId) {
        List<WorkSchedule> schedules = workScheduleService.getWorkSchedulesByPtId(ptId);
        List<WorkScheduleDTO> scheduleDTOs = schedules.stream()
                .map(workScheduleDTOMapper::toWorkScheduleDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleDTOs);
    }
}
