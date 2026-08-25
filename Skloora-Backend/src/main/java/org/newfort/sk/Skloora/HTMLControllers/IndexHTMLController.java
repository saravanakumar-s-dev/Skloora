package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexHTMLController {

    @GetMapping(path = "/home")
    public String IndexHTML(Model model) {
        return "index";
    }

}
