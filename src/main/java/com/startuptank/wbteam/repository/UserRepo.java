package com.startuptank.wbteam.repository;

import com.startuptank.wbteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends  JpaRepository<User,Long> {


}
