package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TokenService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.TokenServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.UserService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;
import ru.kpfu.itis.group11501.utkin.Utils.Hash;
import ru.kpfu.itis.group11501.utkin.Utils.Token;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 25.10.2016.
 */
public class ServletLogin extends HttpServlet {

    UserService userService;
    TokenService tokenService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("err", req.getParameter("err"));
        root.put("login", req.getParameter("login"));
        resp.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(req, resp, "login.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("nickname").toLowerCase();
        String rememberMe = req.getParameter("rememberMe");
        String password = req.getParameter("password");
        String language = req.getParameter("language");
        userService = new UserServiceImpl();
        User currentUser = userService.find(login);

        if (currentUser != null) {
            if (Hash.getMd5Apache(password).equals(currentUser.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("current_user", currentUser);
                if (rememberMe != null) {
                    String token = Token.getToken();
                    Cookie cookie = new Cookie("current_user", token);
                    cookie.setMaxAge(30 * 24 * 60 * 60);
                    resp.addCookie(cookie);
                    tokenService = new TokenServiceImpl();
                    tokenService.addToken("" + currentUser.getNickname(), token);
                }
                Cookie cookie = new Cookie("language", language);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(cookie);
                resp.sendRedirect("/home");
            } else {
                resp.sendRedirect("/login?err=wrong password!&login=" + login + "&language=" + language);
            }
        } else {
            resp.sendRedirect("/login?err=wrong login!&login=" + login + "&language=" + language);
        }
    }
}
