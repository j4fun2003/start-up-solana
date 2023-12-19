package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.repository.ProjectRepo;
import com.startuptank.wbteam.service.serviceImpl.ProjectServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class UserRest {
    @Autowired
    private UserServiceImpl service; // Sử dụng repository để tương tác với cơ sở dữ liệu
    @Autowired
    SessionService sessionService;
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    ProjectRepo projectRepo;
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

    @PostMapping("/saveAddress")
    public ResponseEntity<?> saveAddressNft(@RequestBody String addressNft,@ModelAttribute("project") Project project, @RequestParam("image") MultipartFile multipartFile) {
        project.setAddressNft(addressNft);;
        return ResponseEntity.ok("Public Key saved successfully");
        User user = sessionService.get("UserCurrent");;
        String pImage = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            File dir = new File("E:\\FPT\\TTTN-FALL2023\\wbteam (1)\\src\\main\\resources\\static\\user\\images");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                File savedFile = new File(dir, multipartFile.getOriginalFilename());
                multipartFile.transferTo(savedFile);
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);
            }
        }
        if (pImage != "")
            project.setUser(user);
        project.setProjectImageUrl(pImage);
        Project p = projectService.save(project);

//        return ResponseEntity.ofNullable("no");
    }

}
