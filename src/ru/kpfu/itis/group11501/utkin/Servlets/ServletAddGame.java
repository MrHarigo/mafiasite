package ru.kpfu.itis.group11501.utkin.Servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.GameService;
import ru.kpfu.itis.group11501.utkin.Services.GameServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.UserService;
import ru.kpfu.itis.group11501.utkin.Services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 18.11.2016.
 */
public class ServletAddGame extends HttpServlet {

    UserService userService;
    GameService gameService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gameService = new GameServiceImpl();
        userService = new UserServiceImpl();
        try {
            java.util.Date helpdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            Date date = new Date(helpdate.getTime());
            userService.updateStatistics(request.getParameter("civilian1"),request.getParameter("civilian2"),
                    request.getParameter("civilian3"),request.getParameter("civilian4"),request.getParameter("civilian5"),
                    request.getParameter("civilian6"),request.getParameter("mafia1"),request.getParameter("mafia2"),
                    request.getParameter("don"),request.getParameter("sheriff"),request.getParameter("winner_team"));

            gameService.createGame(request.getParameter("civilian1"),request.getParameter("civilian2"),
                    request.getParameter("civilian3"),request.getParameter("civilian4"),request.getParameter("civilian5"),
                    request.getParameter("civilian6"),request.getParameter("mafia1"),request.getParameter("mafia2"),
                    request.getParameter("don"),request.getParameter("sheriff"),date,request.getParameter("winner_team"));
            response.sendRedirect("/games");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userService = new UserServiceImpl();

        Map<String, Object> root = new HashMap<>();

        User user = (User)request.getSession().getAttribute("current_user");

        root.put("user",user);
        boolean logged = request.getSession().getAttribute("current_user") != null;
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "addgame.ftl",root);
    }
}
