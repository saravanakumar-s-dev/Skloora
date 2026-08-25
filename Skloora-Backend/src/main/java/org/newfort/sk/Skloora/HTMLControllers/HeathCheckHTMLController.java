package org.newfort.sk.Skloora.HTMLControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class HeathCheckHTMLController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
