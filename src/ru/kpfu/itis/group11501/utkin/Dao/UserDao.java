package ru.kpfu.itis.group11501.utkin.Dao;

/**
 * Created by user on 21.10.2016.
 */

import ru.kpfu.itis.group11501.utkin.Models.User;

import java.sql.SQLException;

public interface UserDao {
    void addUser(User user) throws SQLException;
    User findUser(String login);
    void setAvatar(String filepath, User user);
}
