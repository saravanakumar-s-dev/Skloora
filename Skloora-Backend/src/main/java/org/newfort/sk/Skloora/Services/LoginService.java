package org.newfort.sk.Skloora.Services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.newfort.sk.Skloora.Repositorys.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginService {

    @Autowired
    private LoginRepo repo;


    public String Login(String email,
                        String password,
                        HttpServletResponse response)
            throws SQLException {

        String result = repo.Login(email, password);

        if (!result.equals("Wrong password")
                && !result.equals("no user found")) {

            Cookie cookie = new Cookie("rememberMe", result);

            cookie.setHttpOnly(true);
            cookie.setPath("/");

            // 30 days
            cookie.setMaxAge(60 * 60 * 24 * 30);

            // Use true in HTTPS production
            cookie.setSecure(false);

            response.addCookie(cookie);

            return "Success";
        }

        return result;
    }

}
