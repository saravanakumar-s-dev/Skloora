package org.newfort.sk.Skloora.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.newfort.sk.Skloora.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService LS;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpServletResponse response) throws SQLException {

        String result = LS.Login(email, password, response);

        if (result.equalsIgnoreCase("Wrong password")) {

            model.addAttribute("result", "Invalid Credential ❌");
            return "Login";

        } else if (result.equalsIgnoreCase("no user found")) {

            model.addAttribute("result", "No user found for this email.");
            return "Login";

        } else {

            return "redirect:/dashboard";

        }
    }


}