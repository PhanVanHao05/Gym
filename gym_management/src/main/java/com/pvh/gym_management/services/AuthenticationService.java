package com.pvh.gym_management.services;

import com.pvh.gym_management.dtos.AuthenticationRequest;
import com.pvh.gym_management.dtos.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
