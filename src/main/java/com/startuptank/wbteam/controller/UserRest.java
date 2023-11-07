package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {
    @Autowired
    private UserServiceImpl service; // Sử dụng repository để tương tác với cơ sở dữ liệu
    @Autowired
    SessionService sessionService;
    @PostMapping("/savePublicKey")
    public ResponseEntity<String> savePublicKey(@RequestBody String publicKey) {
        User user = sessionService.get("UserCurrent");
        if(user!=null){
            user.setWalletAddress(publicKey); // Điều này giả sử bạn đã có một đối tượng User và một trường publicKey
            service.save(user);
            return ResponseEntity.ok("Public Key saved successfully");
        }

        return ResponseEntity.ofNullable("no");
    }
}
