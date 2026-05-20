package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService fps;

    @PostMapping(value = "/forgot")
    public String Forgot(@RequestParam String email, Model mod)throws SQLException
    {

        String result = fps.Forgot(email);

        mod.addAttribute("result", result);

        return "forgotpassword";

    }

}
