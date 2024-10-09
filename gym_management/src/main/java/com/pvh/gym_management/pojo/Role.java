package com.pvh.gym_management.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity
@Table(name = "gym_role", schema = "gym_managementdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 10)
    private String name;

    @OneToMany(mappedBy = "role")
    private Collection<User> users;

    @Column(name = "authorities")
    private String authorities;

    public Collection<GrantedAuthority> getAuthorities() {
        if (authorities == null || authorities.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
