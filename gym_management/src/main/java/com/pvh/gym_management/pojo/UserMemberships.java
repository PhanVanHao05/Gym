package com.pvh.gym_management.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import com.pvh.gym_management.enums.MembershipStatus;

import java.util.Date;

@Entity
@Table(name = "gym_user_memberships", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMemberships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "tier_id", nullable = false)
    private MembershipTiers membershipTier;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;
}
