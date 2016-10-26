package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TokenService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.TokenServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 25.10.2016.
 */
public class ServletLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie: cookies
                ) {
            if (cookie.getName().equals("current_user")) {
                String current_token = cookie.getValue();
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                TokenService tokenService = new TokenServiceImpl();
                if (tokenService.findToken(current_token)!=null) {
                    tokenService.deleteToken(current_token);
                }
                resp.addCookie(cookie);
            }
        }
        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }
}
