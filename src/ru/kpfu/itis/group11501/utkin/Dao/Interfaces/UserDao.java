package ru.kpfu.itis.group11501.utkin.Dao.Interfaces;

/**
 * Created by user on 21.10.2016.
 */

import ru.kpfu.itis.group11501.utkin.Models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    void addUser(User user) throws SQLException;
    User findUser(String login);
    void setAvatar(String filepath, User user);
    ArrayList<User> getAllUsers();
    void updateUser(User user);
    void addUserToNextMafia(User user);
    ArrayList<User> getUsersForNextMafia();
    public ArrayList<User> searchUsersLike(String pattern);
    void deleteUser(String nickname);
    void updateUserInfo(String oldNickname, String NewNickname, String password, int experience);
}
