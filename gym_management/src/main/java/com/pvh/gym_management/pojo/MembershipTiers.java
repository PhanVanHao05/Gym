package com.pvh.gym_management.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int duration;

    @OneToMany(mappedBy = "membershipTier")
    @JsonIgnore
    private Collection<UserMemberships> userMemberships;
}
