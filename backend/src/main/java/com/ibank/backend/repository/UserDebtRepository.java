package com.ibank.backend.repository;

import com.ibank.backend.entity.UserDebt;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDebtRepository extends JpaRepository<UserDebt, Integer> {
   
}

