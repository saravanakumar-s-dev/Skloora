package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController
{

    @Autowired
    private LoginRepo LR;

    @PostMapping(path = "/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session)throws SQLException
    {

        session.setAttribute("email", email);

       String result =  LR.Login(email, password);

       if(result.equalsIgnoreCase("Wrong password"))
       {
        model.addAttribute("result", "invalid credential ❌");
       }
       else if(result.equalsIgnoreCase("no user found"))
       {
        model.addAttribute("result", "No user found for the email.try another!");
       }
       else
       {
        return "redirect:/dashboard";
       }

       return "Login";

    }

}
