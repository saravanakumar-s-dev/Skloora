package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SendMSGHTMLController {

    @GetMapping(path = "/sendmsg/{id}")
    public String SendMSGHTML(Model model, @PathVariable String id) {

        model.addAttribute("msg_id", id);

        return "SendMSG";
    }

}
