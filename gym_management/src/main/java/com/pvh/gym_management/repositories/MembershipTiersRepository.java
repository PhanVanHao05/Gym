package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.MembershipTiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipTiersRepository extends JpaRepository<MembershipTiers, Integer> {
}
