package com.pvh.gym_management.dtos;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String avatar;
    private String address;
}
