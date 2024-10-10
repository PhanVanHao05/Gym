package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.UserMemberships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMembershipsRepository extends JpaRepository<UserMemberships, Integer> {
    List<UserMemberships> findByUserId(int userId);
}
