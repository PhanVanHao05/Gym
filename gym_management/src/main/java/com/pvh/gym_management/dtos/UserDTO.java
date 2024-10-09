package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Integer id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Boolean isActive;
    private String avatar;
    private String roleName;
}
