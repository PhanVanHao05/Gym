package com.pvh.gym_management.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Entity
@Table(name = "gym_payment_methods", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private Collection<Receipts> receipts;
}
