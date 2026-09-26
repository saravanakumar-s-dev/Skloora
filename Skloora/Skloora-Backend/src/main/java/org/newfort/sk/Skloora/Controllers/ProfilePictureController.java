package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Services.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/profile")
public class ProfilePictureController {

    @Autowired
    private ProfilePictureService service;

    @PostMapping("/update")
    public String SavePic(@RequestParam MultipartFile profileImage, @CookieValue(name = "rememberMe") String token, Model mod) throws SQLException, IOException {

        mod.addAttribute("result", service.profilePicture(token, profileImage));

        return "profileUpdate";

    }
}
