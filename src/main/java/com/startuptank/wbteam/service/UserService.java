package com.startuptank.wbteam.service;

import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepo dao;
    public List<User> findAll(){
        return dao.findAll();
    }
}
