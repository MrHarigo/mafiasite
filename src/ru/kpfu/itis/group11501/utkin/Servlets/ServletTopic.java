package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Comment;
import ru.kpfu.itis.group11501.utkin.Models.Topic;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.CommentServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.TopicServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.CommentService;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TopicService;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 18.11.2016.
 */
public class ServletTopic extends HttpServlet {

    UserService userService;
    TopicService topicService;
    CommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String text = request.getParameter("newComment");
        User user = (User)request.getSession().getAttribute("current_user");
        String[] strings = request.getRequestURI().split("/");
        Topic topic = topicService.findTopic(Integer.parseInt(strings[strings.length - 1]));
        Comment comment = new Comment(user,text, topic);
        commentService = new CommentServiceImpl();
        commentService.addCommentToTopic(comment);
        response.setContentType("text/html; charset=utf-8");
        response.sendRedirect(request.getRequestURI());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        userService = new UserServiceImpl();
        topicService = new TopicServiceImpl();
        commentService = new CommentServiceImpl();

        Map<String, Object> root = new HashMap<>();

        String[] strings = request.getRequestURI().split("/");
        Topic topic = topicService.findTopic(Integer.parseInt(strings[strings.length - 1]));
        User user = (User)request.getSession().getAttribute("current_user");
        ArrayList<Comment> comments = commentService.getCommentsForTopic(topic);

        root.put("user",user);
        root.put("topic",topic);
        root.put("comments",comments);
        boolean logged = request.getSession().getAttribute("current_user") != null;
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "topic.ftl",root);
    }
}
