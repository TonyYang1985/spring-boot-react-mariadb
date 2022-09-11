package com.ibank.backend.repository;

import com.ibank.backend.entity.UserDebt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDebtRepository extends JpaRepository<UserDebt, Integer> {
   
    @Query("SELECT u FROM UserDebt u WHERE u.status  = :status and  u.userId  = :userId")
    public List<UserDebt> findUserDebtByUserId(@Param("status") String status ,@Param("userId") int userId);

    @Query("SELECT SUM(debt) FROM UserDebt u WHERE u.status  = :status and  u.userId  = :userId")
    public double sumUserDebtByUserId(@Param("status") String status ,@Param("userId") int userId);

}

