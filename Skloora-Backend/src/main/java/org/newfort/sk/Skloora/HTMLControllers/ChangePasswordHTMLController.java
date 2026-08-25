package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class ChangePasswordHTMLController {

    @GetMapping(path = "/changePassword")
    public String ChangePassword(Model mod) {
        return "ChangePassword";
    }
}
