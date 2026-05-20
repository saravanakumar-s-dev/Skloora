package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.SendMSGRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SendMSGController
{

    @Autowired
    private SendMSGRepo SMR;

    @PostMapping(path = "/send_msg")
    public String SendMSG(@RequestParam String receiver, @RequestParam String text, Model model, HttpSession session) throws SQLException
    {

        String email = (String) session.getAttribute("email");

        if(SMR.SendMSG(email, receiver, text))
        {

            model.addAttribute("result", "Sent successfully ✅");

        }
        else
        {
            model.addAttribute("result", "Failed to send the mail ❌");
        }

        return "SendMSG";

    }

}
