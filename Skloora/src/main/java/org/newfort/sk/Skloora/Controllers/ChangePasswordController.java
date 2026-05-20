package org.newfort.sk.Skloora.Controllers;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Services.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService cps;

    @PostMapping(value = "/Change")
    public String Change(@RequestParam String password, HttpSession session, Model mod) throws SQLException
    {

        String email = (String) session.getAttribute("email");

        String result = cps.ChangePassword(email, password);

        mod.addAttribute("result", result);

        return "ChangePassword";
    }

}
