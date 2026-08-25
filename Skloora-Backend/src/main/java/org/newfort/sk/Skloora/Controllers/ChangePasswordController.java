package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Services.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/auth")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService service;

    @PostMapping("/change-password-authentication")
    public Object Authentication(@CookieValue(name = "rememberMe", required = false) String token, @RequestParam String email) throws Exception {

        String result = service.Authentication(token, email);

        if (result == "success") {

            return new RedirectView("/skloora/auth/change-password-otp");

        } else if (result == "not your email") {
            return "This is not your email!";
        } else {
            return "something went wrong";
        }

    }

    @GetMapping("/change-password-otp-verify")
    public String ChangePassword(@RequestParam String otp, @RequestParam String password, @CookieValue(name = "rememberMe", required = false) String token) throws Exception {

        String result = service.ChangePassword(otp, token, password);

        return result;

    }
}
