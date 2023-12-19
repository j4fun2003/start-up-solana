package com.startuptank.wbteam.controller;

<<<<<<< HEAD
import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.repository.ProjectRepo;
import com.startuptank.wbteam.service.serviceImpl.ProjectServiceImpl;
=======
import com.startuptank.wbteam.entity.User;
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

@RestController
public class UserRest {
    @Autowired
    private UserServiceImpl service; // Sử dụng repository để tương tác với cơ sở dữ liệu
    @Autowired
    SessionService sessionService;
<<<<<<< HEAD
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    ProjectRepo projectRepo;
=======
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
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
<<<<<<< HEAD

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

=======
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
}
