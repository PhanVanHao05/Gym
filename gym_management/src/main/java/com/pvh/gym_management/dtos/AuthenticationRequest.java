package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {

    @NotNull(message = "{user.userName.notNull}")
    @Size(min = 5, max = 255, message = "{user.userName.size}")
    private String userName;

    @NotNull(message = "{user.password.notNull}")
    @Size(min = 3, max = 255, message = "{user.password.size}")
    private String password;
}
