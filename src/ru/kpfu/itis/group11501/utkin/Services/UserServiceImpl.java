package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Dao.UserDao;
import ru.kpfu.itis.group11501.utkin.Dao.UserDaoImpl;
import ru.kpfu.itis.group11501.utkin.Messages.Message;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Utils.Hash;
import ru.kpfu.itis.group11501.utkin.Verifiers.UserVerify;
import ru.kpfu.itis.group11501.utkin.Errors.Error;

import java.sql.SQLException;

/**
 * Created by user on 25.10.2016.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = null;
    private Error error = null;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    public void add(String nickname, String password, String passwordAgain, int experience) {
        error = null;
        //Checking the size (2<x<32)
        if (defaultSize(nickname) && defaultSize(password) && defaultSize(passwordAgain)) {
            if (password.equals(passwordAgain)) {
                User newUser = null;
                try {
                    password = Hash.getMd5Apache(password);
                    newUser = new User(nickname, password, experience);
                    if (UserVerify.checkUserInBD(userDao, nickname) == null) {
                        userDao.addUser(newUser);
                    }
                    else error = new Error("nickname_taken","This nickname is already taken!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                error = new Error("wrong_password", "Passwords aren't equal!");
            }
        } else {
            error = new Error("wrong_size","Login should be between 2 and 32 symbols");
        }
    }

    @Override
    public void delete(String nickname) {

    }

    public User find(String nickname) {
        error = null;
        if (userDao.findUser(nickname)==null) {
            error = new Error("user_not_found", "User not found");
            return null;
        } else {
            return userDao.findUser(nickname);
        }
    }

    @Override
    public void setAvatar(String filepath, User user) {
        error = null;
        if (user == null) {
            error = new Error("user_not_exists", "This user doesn't exist");
        } else
            userDao.setAvatar(filepath,user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    private boolean defaultSize(String value) {
        return value.length() >= 2 && value.length() <= 32;
    }
}
