package org.newfort.sk.Skloora.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.newfort.sk.Skloora.Services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class CookieController {

    @Autowired
    private CookieService CS;

    @GetMapping("/")
    public String dashboard(
            @CookieValue(name = "rememberMe", required = false) String token)
            throws SQLException {

        if (token == null) {
            return "redirect:/home";
        }

        if (!CS.checkLogin(token)) {
            return "redirect:/home";
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(
            @CookieValue(name = "rememberMe", required = false) String token,
            HttpServletResponse response)
            throws SQLException {

        if (token != null) {
            CS.Logout(token, response);
        }

        return "redirect:/home";
    }
}