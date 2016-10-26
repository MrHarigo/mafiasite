package ru.kpfu.itis.group11501.utkin.Helpers;

import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.TokenServiceImpl;
import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 15.11.2016.
 */
public class CookieToSession {
    public static User add(ServletRequest req){
        Cookie[] cookies = ((HttpServletRequest) req).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("current_user")) {
                    TokenServiceImpl tokenService = new TokenServiceImpl();
                    String nickname = tokenService.findToken(cookie.getValue());
                    UserServiceImpl userService = new UserServiceImpl();
                    return userService.find(nickname);
                }
            }
        }
        return null;
    }
}
