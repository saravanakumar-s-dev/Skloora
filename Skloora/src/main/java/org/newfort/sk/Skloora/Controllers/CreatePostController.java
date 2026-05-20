package org.newfort.sk.Skloora.Controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.CreatePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
public class CreatePostController {

    @Autowired
    private CreatePostRepo CPR;

    @PostMapping(value = "/create_post")
    public String CreatePost(@RequestParam String des, @RequestParam MultipartFile media, Model model, HttpSession session) throws SQLException, IOException
    {

        String email = (String) session.getAttribute("email");

        if(CPR.CreatePost(email, des, media))
        {
            model.addAttribute("result", "Posted successfully ✅");
        }
        else
        {
            model.addAttribute("result", "Failed Posted ❌");
        }

        return "CreatePost";

    }

}
