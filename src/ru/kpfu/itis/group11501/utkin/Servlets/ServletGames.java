package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Game;
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
 * Created by user on 15.11.2016.
 */
public class ServletGames extends HttpServlet {

    GameServiceImpl gameService;
    UserServiceImpl userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> root = new HashMap<>();
        gameService = new GameServiceImpl();
        User user = (User)request.getSession().getAttribute("current_user");
        ArrayList<Game> games = gameService.getAllGames();

        if (request.getParameter("sort") != null) {
            games = gameService.sortGames(request.getParameter("sort"),games);
        }
        root.put("games",games);

        boolean logged = request.getSession().getAttribute("current_user") != null;
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "games.ftl",root);
    }
}
