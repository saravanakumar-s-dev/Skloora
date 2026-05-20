package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatePostHTMLController {

    @GetMapping(value = "/CreatePost")
    public String CreatePostHTML(Model model)
    {
        return "CreatePost";
    }
}
