package com.ibank.backend.repository;

import com.ibank.backend.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      /**
     * Finds User by using the first name as a search criteria.
     * @param firstname
     * @return  A list of User whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstname) = LOWER(:firstname)")
    public List<User> findByName(@Param("firstname") String firstname);

          /**
     * Finds User by using the first name as a search criteria.
     * @param firstname
     * @return  A list of User whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    public List<User> findByEmail(@Param("email") String email);


          /**
     * Finds User by using the first name as a search criteria.
     * @param firstname
     * @return  A list of User whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT u FROM User u WHERE u.id  = :id")
    public User findByUserId(@Param("id") int id);
}

