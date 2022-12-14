package com.ibank.backend.repository;

import com.ibank.backend.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
   
    @Query("SELECT u FROM UserBalance u WHERE u.userId  = :userId")
    public UserBalance finBalanceByUserId(@Param("userId") int userId);

}

