package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "gym_work_schedule", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pt_id", nullable = false)
    private PTDetail pt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(name = "work_day")
    private LocalDate workDay; // Ngày làm việc

    @Column(name = "start_time")
    private LocalTime startTime; // Thời gian bắt đầu

    @Column(name = "end_time")
    private LocalTime endTime; // Thời gian kết thúc
}
