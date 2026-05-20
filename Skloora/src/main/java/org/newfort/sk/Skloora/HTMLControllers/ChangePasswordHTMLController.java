package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePasswordHTMLController {

    @GetMapping(path = "/changePassword")
    public String ChangePassword(Model mod)
    {
        return "ChangePassword";
    }
}
