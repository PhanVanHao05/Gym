package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.enums.MembershipStatus;
import com.pvh.gym_management.pojo.MembershipTiers;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.pojo.UserMemberships;
import com.pvh.gym_management.repositories.MembershipTiersRepository;
import com.pvh.gym_management.repositories.UserMembershipsRepository;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.services.UserMembershipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMembershipsServiceImpl implements UserMembershipsService {
    @Autowired
    private UserMembershipsRepository userMembershipsRepository;
    @Autowired
    private MembershipTiersRepository membershipTiersRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserMemberships createUserMembership(int userId, int tierId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        MembershipTiers tier = membershipTiersRepository.findById(tierId).orElseThrow(() -> new RuntimeException("Tier not found"));

        UserMemberships userMembership = new UserMemberships();
        userMembership.setUser(user);
        userMembership.setMembershipTier(tier);

        Date startDate = new Date();
        userMembership.setStartDate(startDate);

        int duration = tier.getDuration();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, duration);
        userMembership.setEndDate(calendar.getTime());

        userMembership.setStatus(MembershipStatus.ACTIVE);

        return userMembershipsRepository.save(userMembership);
    }

    @Override
    public List<UserMemberships> getAllUserMemberships() {
        return userMembershipsRepository.findAll();
    }

    @Override
    public boolean deleteUserMembership(int id) {
        Optional<UserMemberships> optionalMembership = userMembershipsRepository.findById(id);
        if (optionalMembership.isPresent()) {
            userMembershipsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<UserMemberships> getUserMembershipsByUserId(int userId) {
        List<UserMemberships> userMemberships = userMembershipsRepository.findByUserId(userId);
        Date currentDate = new Date();

        List<UserMemberships> updatedMemberships = userMemberships.stream().map(membership -> {
            if (membership.getEndDate() != null
                    && membership.getEndDate().before(currentDate)
                    && membership.getStatus() != MembershipStatus.EXPIRED) {
                membership.setStatus(MembershipStatus.EXPIRED);
                userMembershipsRepository.save(membership);
            }
            return membership;
        }).collect(Collectors.toList());

        return updatedMemberships;
    }

    @Override
    public UserMemberships updateMembershipStatus(int id, MembershipStatus status) {
        Optional<UserMemberships> optionalMembership = userMembershipsRepository.findById(id);
        if (optionalMembership.isPresent()) {
            UserMemberships userMembership = optionalMembership.get();
            userMembership.setStatus(status);
            return userMembershipsRepository.save(userMembership);
        }
        return null;
    }

    @Override
    public List<UserMemberships> getActiveUserMembershipsByUserId(int userId) {
        List<UserMemberships> userMemberships = userMembershipsRepository.findByUserId(userId);
        Date currentDate = new Date();
        return userMemberships.stream()
                .filter(membership -> {
                    if (membership.getEndDate() != null && membership.getEndDate().before(currentDate)) {
                        membership.setStatus(MembershipStatus.EXPIRED);
                        userMembershipsRepository.save(membership);
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
