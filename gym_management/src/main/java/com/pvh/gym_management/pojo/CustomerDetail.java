package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "gym_customer_detail", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private double weight;
    private double height;
}
