package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);
    List<User> findByIsActiveFalse();
    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.role.name = 'PT' AND u.id NOT IN " +
            "(SELECT ws.pt.id FROM WorkSchedule ws WHERE ws.workDay = :workDay " +
            "AND ((ws.startTime <= :startTime AND ws.endTime > :startTime) " +
            "OR (ws.startTime < :endTime AND ws.endTime >= :endTime) " +
            "OR (ws.startTime >= :startTime AND ws.endTime <= :endTime)))")
    List<User> findAvailablePTs(@Param("workDay") LocalDate workDay,
                                @Param("startTime") LocalTime startTime,
                                @Param("endTime") LocalTime endTime);

}
