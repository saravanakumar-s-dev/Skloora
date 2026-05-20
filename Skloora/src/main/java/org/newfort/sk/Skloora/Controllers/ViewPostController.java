package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Services.ViewPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewPostController {

    @Autowired
    private ViewPostService VPS;

    @GetMapping(value = "/getAllPost")
    public String getAllPost(Model model) throws SQLException
    {
        model.addAttribute("posts", VPS.getAllPost());

        return "ViewPost";
    }

}
