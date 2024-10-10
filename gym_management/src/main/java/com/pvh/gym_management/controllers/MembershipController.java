package com.pvh.gym_management.controllers;

import com.pvh.gym_management.pojo.MembershipTiers;
import com.pvh.gym_management.services.MembershipTiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-tiers")
public class MembershipController {

    @Autowired
    private MembershipTiersService membershipTiersService;

    @PostMapping("/create")
    public ResponseEntity<MembershipTiers> createMembershipTier(@RequestBody MembershipTiers membershipTier) {
        MembershipTiers createdTier = membershipTiersService.createMembershipTier(membershipTier);
        return new ResponseEntity<>(createdTier, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMembershipTier(@PathVariable int id) {
        boolean isDeleted = membershipTiersService.deleteMembershipTier(id);
        if (isDeleted) {
            return new ResponseEntity<>("Membership Tier deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Membership Tier not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<MembershipTiers>> getAllMembershipTiers() {
        List<MembershipTiers> tiersList = membershipTiersService.getAllMembershipTiers();
        return new ResponseEntity<>(tiersList, HttpStatus.OK);
    }

}
