package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupHTMLController
{

    @GetMapping(path = "/Signup")
    public String signupHTML(Model model)
    {

        return "Signup";

    }

}
