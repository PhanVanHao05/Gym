package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gym_pt_detail", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PTDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private double salary;

    @OneToMany(mappedBy = "pt")
    private Collection<WorkSchedule> workSchedules;
}
