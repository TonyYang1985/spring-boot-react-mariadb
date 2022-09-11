package com.ibank.backend.repository;

import com.ibank.backend.entity.UserDebt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDebtRepository extends JpaRepository<UserDebt, Integer> {
   
    @Query("SELECT u FROM UserDebt u WHERE u.userId  = :userId")
    public List<UserDebt> findUserDebtByUserId(@Param("userId") int userId);
}

