package com.pvh.gym_management.services;

import com.pvh.gym_management.dtos.*;
import com.pvh.gym_management.pojo.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserByEmail(String email);
    Optional<User> findByUsername(String username);
    User saveUser(User user);
    Optional<UserPublicDTO> getUserById(int id);
    void deleteUserById(int id);
    List<UserPublicDTO> findInactiveUsers();
    UserPublicDTO toggleUserActiveStatus(int id);
    UserPublicDTO updateUser(int userId, UserUpdateDTO userUpdateDTO);
    UserDTO updateUserRoleToPT(int userId);
    void resetPassword(String username);
    void changePassword(int userId, ChangePasswordRequest request);
    List<UserDTO> getAllUsers();
    List<PTInfoDTO> getAllPTDetails();
    List<User> getAvailableUsersWithoutWorkSchedule(LocalDate workDay, LocalTime startTime, LocalTime endTime);
}
