package com.startuptank.wbteam.service.serviceImpl;

import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.repository.UserRepo;
import com.startuptank.wbteam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public User login(String email, String password) {
        try {
            return userRepo.login(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean activeUser(User user) {
        if(user.getActive()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepo.existsUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }


}
