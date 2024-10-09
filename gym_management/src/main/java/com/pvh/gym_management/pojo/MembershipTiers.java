package com.pvh.gym_management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gym_membership_tiers", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipTiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String benefits;
    private double price;

    @OneToMany(mappedBy = "membershipTier")
    private Collection<UserMemberships> userMemberships;
}
