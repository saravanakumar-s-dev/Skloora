package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Repositorys.SendMSGRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class SendMSGController {

    @Autowired
    private SendMSGRepo SMR;

    @PostMapping(path = "/send_msg/{id}")
    public String SendMSG(@RequestParam String text, Model model, @CookieValue(name = "rememberMe", required = false) String token, @PathVariable String id) throws SQLException {

        if (SMR.SendMSG(token, id, text)) {

            model.addAttribute("result", "Sent successfully ✅");

        } else {
            model.addAttribute("result", "Failed to send the mail ❌");
        }

        return "SendMSG";

    }

}
