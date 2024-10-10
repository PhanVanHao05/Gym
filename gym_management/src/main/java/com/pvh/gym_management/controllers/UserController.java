package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.*;
import com.pvh.gym_management.mappers.UserDTOMapper;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicDTO> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<UserPublicDTO> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        UserPublicDTO userPublicDTO = new UserPublicDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getAddress(),
                savedUser.getAvatar(),
                savedUser.isActive()
        );
        return ResponseEntity.ok(userPublicDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        return userService.getUserById(id)
                .map(user -> {
                    userService.deleteUserById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<UserPublicDTO>> getInactiveUsers() {
        List<UserPublicDTO> inactiveUsers = userService.findInactiveUsers();
        return ResponseEntity.ok(inactiveUsers);
    }

    @PutMapping("/{id}/toggle-active")
    public ResponseEntity<UserPublicDTO> toggleUserActiveStatus(@PathVariable int id) {
        UserPublicDTO updatedUser = userService.toggleUserActiveStatus(id);
        return ResponseEntity.ok(updatedUser);
    }

    //Api cập nhật thông tin người dùng
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserPublicDTO> updateUser(@PathVariable int userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserPublicDTO updatedUser = userService.updateUser(userId, userUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    //Admin có thể gọi api này để đổi vai tr của user thành PT
    @PutMapping("/register-pt/{id}")
    public ResponseEntity<UserDTO> registerAsPT(@PathVariable int id) {
        UserDTO updatedUser = userService.updateUserRoleToPT(id);
        return ResponseEntity.ok(updatedUser);
    }

    //Api quên mật khẩu
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String username) {
        userService.resetPassword(username);
        return ResponseEntity.ok("Mật khẩu đã được đặt lại thành công. Vui lòng kiểm tra email của bạn.");
    }

    //Api đổi mật khẩu mới
    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable int id, @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request);
        return ResponseEntity.ok("Mật khẩu đã được thay đổi thành công.");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/pt")
    public ResponseEntity<List<PTInfoDTO>> getAllPTDetails() {
        List<PTInfoDTO> ptList = userService.getAllPTDetails();
        return ResponseEntity.ok(ptList);
    }

    @GetMapping("/pt/available")
    public ResponseEntity<?> getAvailableUsersWithoutWorkSchedule(
            @RequestParam("workDay") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workDay,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {

        List<User> users = userService.getAvailableUsersWithoutWorkSchedule(workDay, startTime, endTime);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có PT nào khả dụng trong khoảng thời gian này.");
        }

        List<UserDTO> userDTOs = users.stream()
                .map(userDTOMapper::toUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

}
