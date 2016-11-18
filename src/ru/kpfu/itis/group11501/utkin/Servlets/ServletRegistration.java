package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 15.11.2016.
 */
public class ServletRegistration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String nickname = request.getParameter("nickname").toLowerCase();
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        int experience = Integer.parseInt(request.getParameter("experience"));

        UserServiceImpl userService = new UserServiceImpl();
        userService.add(nickname,password,passwordConfirmation,experience);
        if (userService.getError()==null) {
            response.sendRedirect("/login");
        } else
            response.sendRedirect("/register?err=" + userService.getError().getMessage());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        Map<String, Object> root = new HashMap<>();
        root.put("err", request.getParameter("err"));
       TemplateHelper.render(request,response,"registration.ftl",root);
    }
}
