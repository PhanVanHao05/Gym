package com.pvh.gym_management.services;

import com.pvh.gym_management.enums.MembershipStatus;
import com.pvh.gym_management.pojo.UserMemberships;
import java.util.List;

public interface UserMembershipsService {
    UserMemberships createUserMembership(int userId, int tierId);
    List<UserMemberships> getAllUserMemberships();
    boolean deleteUserMembership(int id);
    List<UserMemberships> getUserMembershipsByUserId(int userId);
    UserMemberships updateMembershipStatus(int id, MembershipStatus status);
    List<UserMemberships> getActiveUserMembershipsByUserId(int userId);
}
