package com.startuptank.wbteam.repository;

import com.startuptank.wbteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends  JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User login(String email, String password);
    Boolean existsUserByEmail(String email);
    User findUserByUserId(Integer id);
}
