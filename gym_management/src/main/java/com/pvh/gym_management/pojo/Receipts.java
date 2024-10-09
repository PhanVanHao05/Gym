package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "gym_receipts", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "membership_tier_id", nullable = false)
    private MembershipTiers membershipTier;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethods paymentMethod;

    @Column(name = "receipt_date")
    private Date receiptDate;

    private double amount;
}
