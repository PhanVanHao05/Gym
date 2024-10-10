package com.pvh.gym_management.controllers;

import com.pvh.gym_management.enums.MembershipStatus;
import com.pvh.gym_management.pojo.UserMemberships;
import com.pvh.gym_management.services.UserMembershipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-memberships")
public class UserMembershipsController {

    @Autowired
    private UserMembershipsService userMembershipsService;

    @PostMapping("/create")
    public ResponseEntity<UserMemberships> createUserMembership(@RequestBody Map<String, Integer> request) {
        int userId = request.get("user_id");
        int tierId = request.get("tier_id");
        UserMemberships createdMembership = userMembershipsService.createUserMembership(userId, tierId);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserMemberships>> getAllUserMemberships() {
        List<UserMemberships> memberships = userMembershipsService.getAllUserMemberships();
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserMembership(@PathVariable int id) {
        boolean isDeleted = userMembershipsService.deleteUserMembership(id);
        if (isDeleted) {
            return new ResponseEntity<>("User Membership deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Membership not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMemberships>> getUserMembershipsByUserId(@PathVariable int userId) {
        List<UserMemberships> userMemberships = userMembershipsService.getUserMembershipsByUserId(userId);
        if (userMemberships.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMemberships, HttpStatus.OK);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateMembershipStatus(@PathVariable int id, @RequestParam MembershipStatus status) {
        UserMemberships updatedMembership = userMembershipsService.updateMembershipStatus(id, status);
        if (updatedMembership != null) {
            return ResponseEntity.ok(updatedMembership);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User membership with ID " + id + " not found");
        }
    }

    @GetMapping("/user/active/{userId}")
    public ResponseEntity<?> getActiveUserMembershipsByUserId(@PathVariable int userId) {
        List<UserMemberships> activeMemberships = userMembershipsService.getActiveUserMembershipsByUserId(userId);
        if (activeMemberships.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Bạn không có gói nào khả dụng, hãy đăng ký gói mới.");
        }
        return ResponseEntity.ok(activeMemberships);
    }
}
