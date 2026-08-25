package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Model.Developers;
import org.newfort.sk.Skloora.Services.DevelopersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DevelopersService DS;

    @GetMapping("/dashboard")
    public String Dashboard(
            Model model,
            @CookieValue(name = "rememberMe", required = false) String token)
            throws SQLException {

        if (token == null) {
            return "redirect:/home";
        }

        List<Developers> developers = DS.getAllDevs(token);

        model.addAttribute("developers", developers);

        return "Dashboard";
    }

    @GetMapping("/searchDevelopers")
    public String searchDevelopers(
            @RequestParam("query") String query,
            Model model,
            @CookieValue(name = "rememberMe", required = false) String token)
            throws SQLException {

        if (token == null) {
            return "redirect:/home";
        }

        List<Developers> developers =
                DS.serchDev(query, token);

        model.addAttribute("developers", developers);

        return "Dashboard";
    }
}