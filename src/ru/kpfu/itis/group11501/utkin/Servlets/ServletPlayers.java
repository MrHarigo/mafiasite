package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.GameServiceImpl;
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
 * Created by user on 16.11.2016.
 */
public class ServletPlayers extends HttpServlet {
    GameServiceImpl gameService;
    UserServiceImpl userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        userService = new UserServiceImpl();
        ArrayList<User> users = userService.getAllUsers();
        boolean logged = request.getSession().getAttribute("current_user") != null;
        if (request.getParameter("sort") != null) {
            users = userService.sortUsers(request.getParameter("sort"),users);
        }
        if (request.getParameter("searchfield") != null) {
            users = userService.searchUsersLike(request.getParameter("searchfield"));
        }

        //pagination
        int number = 1;
        if (request.getParameter("number") != null) {
            number = Integer.parseInt(request.getParameter("number"));
        }

        int i =  (users.size() / 5) + 1 ;

        ArrayList<User> userspage = new ArrayList<>();

        for (int q = number * 5 - 5; q < number * 5; q++){
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

        root.put("users",userspage);
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "players.ftl",root);
    }
}
