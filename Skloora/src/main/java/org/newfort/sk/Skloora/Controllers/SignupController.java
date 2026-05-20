package org.newfort.sk.Skloora.Controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.newfort.sk.Skloora.Services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController
{

    @Autowired
    private SignupService SS;

    @PostMapping(path = "/signup")
    public String Signup(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String des, Model model) throws SQLException, IOException
    {

        String result = SS.SignupServ(email, username, password, des);

        if(result.equalsIgnoreCase("user already exists"))
        {
            model.addAttribute("result", "User already exists with the email or username");
        }
        else if(result.equalsIgnoreCase("Success"))
        {
            model.addAttribute("result", "Account successfully created ✅");
        }
        else
        {
            model.addAttribute("result", "Failed to create the account ❌");
        }


        return "Signup";

    }

}
