package com.pvh.gym_management.services;

import com.pvh.gym_management.pojo.MembershipTiers;

import java.util.List;

public interface MembershipTiersService {
    MembershipTiers createMembershipTier(MembershipTiers membershipTier);
    boolean deleteMembershipTier(int id);
    List<MembershipTiers> getAllMembershipTiers();

}
