package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Services.ViewMSGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class MSGViewController {

    @Autowired
    private ViewMSGService VMS;

    @GetMapping(path = "/getAllMSG")
    public String MVGView(Model model, @CookieValue(name = "rememberMe", required = false) String token) throws SQLException {

        model.addAttribute("messages", VMS.getAllMSG(token));

        return "ViewMSG";

    }

}
