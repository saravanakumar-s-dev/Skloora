package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.newfort.sk.Skloora.Getters_Setters.Developers;
import org.newfort.sk.Skloora.Services.DevelopersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController 
{

    @Autowired
    private DevelopersService DS;

    @GetMapping(path = "/dashboard")
    public String Dashboard(Model model, HttpSession session) throws SQLException
    {

        String email = (String) session.getAttribute("email");


        List<Developers> developers = DS.getAllDevs(email);

        
        model.addAttribute("developers", developers);

        return "Dashboard";



    }
}
