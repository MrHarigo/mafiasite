package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 24.11.2016.
 */
public class ServletAdminUsers extends HttpServlet {
    UserServiceImpl userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        userService = new UserServiceImpl();

        if (request.getParameter("par").equals("adduser")) {
            String nickname = request.getParameter("nickname").toLowerCase();
            String password = request.getParameter("password");
            int experience = Integer.parseInt(request.getParameter("experience"));
            userService.add(nickname, password, password, experience);
            response.sendRedirect("/admin/users");
        }
        if (request.getParameter("par").equals("deleteuser")) {
            String nickname = request.getParameter("nickname");
            userService.delete(nickname);
            response.sendRedirect("/admin/users");
        }
        if (request.getParameter("par").equals("updateuser")) {
            String nickname = request.getParameter("nickname");
            String newNickname = request.getParameter("nicknameof" + nickname);
            String newPassword = request.getParameter("passwordof" + nickname);
            String newExperienceString = request.getParameter("experienceof" + nickname);
            int newExperience = Integer.parseInt(newExperienceString);
            userService.updateUserInfo(nickname,newNickname, newPassword, newExperience);
            response.sendRedirect("/admin/users");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        userService = new UserServiceImpl();
        ArrayList<User> users = userService.getAllUsers();
        boolean logged = request.getSession().getAttribute("current_user") != null;
        Map<String, Object> root = new HashMap<>();

        if (request.getParameter("search-input") != null) {
            users = userService.searchUsersLike(request.getParameter("search-input"));
        }

        if (request.getParameter("sort") != null) {
            users = userService.sortUsers(request.getParameter("sort"),users);
        }

        //pagination
        int number = 1;
        if (request.getParameter("number") != null) {
            number = Integer.parseInt(request.getParameter("number"));
        }

        int i =  (users.size() / 10) + 1 ;

        ArrayList<User> userspage = new ArrayList<>();

        for (int q = number * 10 - 10; q < number * 10; q++){
            if (q == users.size())
                break;
            userspage.add(users.get(q));
        }
        root.put("number",number);
        root.put("max", i);

        ArrayList<Integer> page = new ArrayList<>();
        for (int q = 1; q <= i; q++)
            page.add(q);

        root.put("page", page);
        root.put("sort", request.getParameter("sort"));
        root.put("search", request.getParameter("search-input"));

        root.put("users", userspage);
        root.put("logged", logged);

        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "adminusers.ftl", root);
    }
}
