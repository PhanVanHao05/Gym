package com.pvh.gym_management.repositories;

import com.pvh.gym_management.pojo.PTComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PTCommentsRepository extends JpaRepository<PTComments, Integer> {
    List<PTComments> findBySchedule_Pt_Id(int ptId);
}

