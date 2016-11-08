package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Comment;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.CommentService;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.CommentServiceImpl;
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
 * Created by user on 18.11.2016.
 */
public class ServletGame extends HttpServlet {

    GameServiceImpl gameService;
    UserServiceImpl userService;
    CommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String text = request.getParameter("newComment");
        User user = (User)request.getSession().getAttribute("current_user");
        String[] strings = request.getRequestURI().split("/");
        Game game = gameService.findGame(Integer.parseInt(strings[strings.length - 1]));
        Comment comment = new Comment(user,text,game);
        commentService = new CommentServiceImpl();
        commentService.addCommentToGame(comment);
        response.setContentType("text/html; charset=utf-8");
        response.sendRedirect(request.getRequestURI());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        gameService = new GameServiceImpl();
        userService = new UserServiceImpl();
        commentService = new CommentServiceImpl();

        Map<String, Object> root = new HashMap<>();

        String[] strings = request.getRequestURI().split("/");
        Game game = gameService.findGame(Integer.parseInt(strings[strings.length - 1]));
        User user = (User)request.getSession().getAttribute("current_user");
        ArrayList<Comment> comments = commentService.getCommentsForGame(game);

        root.put("user",user);
        root.put("game",game);
        root.put("comments",comments);
        boolean logged = request.getSession().getAttribute("current_user") != null;
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "game.ftl",root);

    }
}
