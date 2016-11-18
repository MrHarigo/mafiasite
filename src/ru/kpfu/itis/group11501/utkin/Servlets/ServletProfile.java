package ru.kpfu.itis.group11501.utkin.Servlets;

import ru.kpfu.itis.group11501.utkin.Helpers.AddPhoto;
import ru.kpfu.itis.group11501.utkin.Helpers.TemplateHelper;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.GameServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.UserServiceImpl;
import ru.kpfu.itis.group11501.utkin.Utils.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 15.11.2016.
 */
@MultipartConfig
public class ServletProfile extends HttpServlet {

    GameServiceImpl gameService;
    UserServiceImpl userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String image;
        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = "profile/"+ Token.getToken()+".jpg";
            AddPhoto.addPhoto(filePart, image);
            User user = (User)request.getSession().getAttribute("current_user");
            userService = new UserServiceImpl();
            userService.setAvatar(image,user);
            request.getSession().invalidate();
        }
        response.sendRedirect("/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gameService = new GameServiceImpl();

        Map<String, Object> root = new HashMap<>();

        User user = (User)request.getSession().getAttribute("current_user");
        ArrayList<Game> games = gameService.findGamesWithUser(user);

        root.put("user",user);
        root.put("games",games);
        boolean logged = request.getSession().getAttribute("current_user") != null;
        root.put("logged", logged);
        response.setContentType("text/html; charset=utf-8");
        TemplateHelper.render(request, response, "profile.ftl",root);
    }
}
