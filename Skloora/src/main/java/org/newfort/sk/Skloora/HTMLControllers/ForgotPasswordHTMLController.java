package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPasswordHTMLController {

    @GetMapping(path = "/ForgotPassword")
    public String ForgotPassword(Model model)
    {
        return "forgotpassword";
    }

}
