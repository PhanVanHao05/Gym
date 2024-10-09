package com.pvh.gym_management.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "gym_user", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PTDetail ptDetail;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerDetail customerDetail;

    @OneToMany(mappedBy = "customer")
    private Collection<PTComments> ptComments;

    @OneToMany(mappedBy = "user")
    private Collection<Receipts> receipts;

    @OneToMany(mappedBy = "customer")
    private Collection<WorkSchedule> workSchedules;

    @OneToMany(mappedBy = "user")
    private Collection<UserMemberships> userMemberships;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null && role.getAuthorities() != null) {
            return role.getAuthorities();
        }
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
