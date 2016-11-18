package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Models.User;

import java.util.ArrayList;

/**
 * Created by user on 25.10.2016.
 */
public interface UserService {
    void add(String nickname, String password, String passwordAgain, int experience) ;
    void delete(String nickname);
    User find(String nickname);
    void setAvatar(String filepath, User user);
    ArrayList<User> getAllUsers();
}
