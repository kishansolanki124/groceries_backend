package com.groceries.groceries.repositories;

import com.groceries.groceries.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String emailId);

    Boolean existsByUserId(String user_id);

    @Query(value = "SELECT * FROM user where mobile=:mobile and deleted_date IS NULL",
            nativeQuery = true)
    User findByMobile(@Param("mobile") String mobile);
}