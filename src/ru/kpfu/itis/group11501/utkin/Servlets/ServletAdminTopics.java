package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Comment;
import ru.kpfu.itis.group11501.utkin.Models.Topic;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.TopicServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TopicService;

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
public class ServletAdminTopics extends HttpServlet {
    TopicService topicService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        topicService = new TopicServiceImpl();
        ArrayList<Topic> topics = topicService.getAllTopics();
        Map<String, Object> root = new HashMap<>();

        root.put("topics",topics);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "admintopics.ftl", root);
    }
}
