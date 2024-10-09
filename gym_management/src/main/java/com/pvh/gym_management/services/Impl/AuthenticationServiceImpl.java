package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.AuthenticationRequest;
import com.pvh.gym_management.dtos.AuthenticationResponse;
import com.pvh.gym_management.dtos.UserDTO;
import com.pvh.gym_management.mappers.UserDTOMapper;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.services.AuthenticationService;
import com.pvh.gym_management.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUserName(authenticationRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateToken(user);

        UserDTO userDetails = userDTOMapper.toUserDTO(user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .userDetails(userDetails)
                .build();
    }
}
