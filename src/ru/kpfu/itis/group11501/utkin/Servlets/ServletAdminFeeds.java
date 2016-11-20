package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Feed;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.FeedServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.FeedService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 21.11.2016.
 */
public class ServletAdminFeeds extends HttpServlet {
    FeedService feedService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        feedService = new FeedServiceImpl();
        ArrayList<Feed> feeds = feedService.get3LastFeed();
        Map<String, Object> root = new HashMap<>();

        root.put("feeds",feeds);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "adminfeeds.ftl", root);
    }
}
