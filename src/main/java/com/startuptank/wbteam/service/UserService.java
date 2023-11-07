package com.startuptank.wbteam.service;

import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
  User login(String email,String password);

  Boolean activeUser(User user);

  Boolean existsUserByEmail(String email);
  User save(User user);

}
