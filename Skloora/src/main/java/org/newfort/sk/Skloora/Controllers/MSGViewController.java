package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Services.ViewMSGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MSGViewController 
{

    @Autowired
    private ViewMSGService VMS;

    @GetMapping(path = "/getAllMSG")
    public String MVGView(Model model, HttpSession session) throws SQLException
    {

        String email = (String) session.getAttribute("email");


        model.addAttribute("messages", VMS.getAllMSG(email));

        return "ViewMSG";

    }

}
