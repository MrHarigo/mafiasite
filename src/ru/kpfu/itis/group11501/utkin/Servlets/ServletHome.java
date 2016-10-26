package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Feed;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.FeedService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.FeedServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.UserService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 18.10.2016.
 */
@WebServlet(name = "ServletHome")
public class ServletHome extends HttpServlet {
    UserService userService;
    FeedService feedService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        feedService = new FeedServiceImpl();
        userService = new UserServiceImpl();
        boolean logged = request.getSession().getAttribute("current_user") != null;
        ArrayList<Feed> feeds = feedService.get3LastFeed();
        root.put("feeds", feeds);
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "home.ftl",root);
    }
}
