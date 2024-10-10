package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptsRepository extends JpaRepository<Receipts, Integer> {
}
