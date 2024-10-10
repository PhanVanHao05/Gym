package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.pojo.MembershipTiers;
import com.pvh.gym_management.repositories.MembershipTiersRepository;
import com.pvh.gym_management.services.MembershipTiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipTiersServiceImpl implements MembershipTiersService {
    @Autowired
    private MembershipTiersRepository membershipTiersRepository;

    @Override
    public MembershipTiers createMembershipTier(MembershipTiers membershipTier) {
        return membershipTiersRepository.save(membershipTier);
    }

    @Override
    public boolean deleteMembershipTier(int id) {
        Optional<MembershipTiers> optionalTier = membershipTiersRepository.findById(id);
        if (optionalTier.isPresent()) {
            membershipTiersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MembershipTiers> getAllMembershipTiers() {
        return membershipTiersRepository.findAll();
    }
}
