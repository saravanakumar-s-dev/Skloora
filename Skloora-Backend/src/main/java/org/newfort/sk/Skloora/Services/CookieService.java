package org.newfort.sk.Skloora.Services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.newfort.sk.Skloora.Repositorys.CookieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CookieService {

    @Autowired
    private CookieRepo repo;

    public boolean checkLogin(String token) throws SQLException {

        return repo.isValidToken(token);

    }

    public void Logout(String token, HttpServletResponse response) throws SQLException {

        repo.Logout(token);

        Cookie cookie = new Cookie("rememberMe", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(864000);

        response.addCookie(cookie);
    }
}
