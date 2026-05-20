package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SendMSGHTMLController {

    @GetMapping(path = "/sendmsg")
    public String SendMSGHTML(Model model) {
        return "SendMSG";
    }

}
