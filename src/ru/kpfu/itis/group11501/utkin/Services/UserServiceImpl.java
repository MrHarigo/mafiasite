package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Dao.UserDao;
import ru.kpfu.itis.group11501.utkin.Dao.UserDaoImpl;
import ru.kpfu.itis.group11501.utkin.Messages.Message;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Utils.Hash;
import ru.kpfu.itis.group11501.utkin.Verifiers.UserVerify;
import ru.kpfu.itis.group11501.utkin.Errors.Error;

import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public ArrayList<User> getAllUsers() {
        error = null;
        if (userDao.getAllUsers()==null) {
            error = new Error("users_not_found", "Users are not found");
            return null;
        } else {
            return userDao.getAllUsers();
        }
    }

    @Override
    public void updateStatistics(String civilian_1, String civilian_2, String civilian_3, String civilian_4,
                                 String civilian_5, String civilian_6, String mafia_1, String mafia_2,
                                 String don, String sheriff, String winner_team) {
        if (winner_team.equals("red")) {
            userDao.findUser(civilian_1).setGames_won_as_civilian(userDao.findUser(civilian_1).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_1).setGames_won(userDao.findUser(civilian_1).getGames_won() + 1);
            userDao.findUser(civilian_1).setGames_played(userDao.findUser(civilian_1).getGames_played() + 1);
            userDao.findUser(civilian_1).setGames_won_as_red(userDao.findUser(civilian_1).getGames_won_as_red() + 1);

            userDao.findUser(civilian_2).setGames_won_as_civilian(userDao.findUser(civilian_2).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_2).setGames_won(userDao.findUser(civilian_2).getGames_won() + 1);
            userDao.findUser(civilian_2).setGames_played(userDao.findUser(civilian_2).getGames_played() + 1);
            userDao.findUser(civilian_2).setGames_won_as_red(userDao.findUser(civilian_2).getGames_won_as_red() + 1);

            userDao.findUser(civilian_3).setGames_won_as_civilian(userDao.findUser(civilian_3).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_3).setGames_won(userDao.findUser(civilian_3).getGames_won() + 1);
            userDao.findUser(civilian_3).setGames_played(userDao.findUser(civilian_3).getGames_played() + 1);
            userDao.findUser(civilian_3).setGames_won_as_red(userDao.findUser(civilian_3).getGames_won_as_red() + 1);

            userDao.findUser(civilian_4).setGames_won_as_civilian(userDao.findUser(civilian_4).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_4).setGames_won(userDao.findUser(civilian_4).getGames_won() + 1);
            userDao.findUser(civilian_4).setGames_played(userDao.findUser(civilian_4).getGames_played() + 1);
            userDao.findUser(civilian_4).setGames_won_as_red(userDao.findUser(civilian_4).getGames_won_as_red() + 1);

            userDao.findUser(civilian_5).setGames_won_as_civilian(userDao.findUser(civilian_5).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_5).setGames_won(userDao.findUser(civilian_5).getGames_won() + 1);
            userDao.findUser(civilian_5).setGames_played(userDao.findUser(civilian_5).getGames_played() + 1);
            userDao.findUser(civilian_5).setGames_won_as_red(userDao.findUser(civilian_5).getGames_won_as_red() + 1);

            userDao.findUser(civilian_6).setGames_won_as_civilian(userDao.findUser(civilian_6).getGames_won_as_civilian() + 1);
            userDao.findUser(civilian_6).setGames_won(userDao.findUser(civilian_6).getGames_won() + 1);
            userDao.findUser(civilian_6).setGames_played(userDao.findUser(civilian_6).getGames_played() + 1);
            userDao.findUser(civilian_6).setGames_won_as_red(userDao.findUser(civilian_6).getGames_won_as_red() + 1);

            userDao.findUser(mafia_1).setGames_lost_as_mafia(userDao.findUser(mafia_1).getGames_lost_as_mafia() + 1);
            userDao.findUser(mafia_1).setGames_lost(userDao.findUser(mafia_1).getGames_lost() + 1);
            userDao.findUser(mafia_1).setGames_played(userDao.findUser(mafia_1).getGames_played() + 1);
            userDao.findUser(mafia_1).setGames_lost_as_black(userDao.findUser(mafia_1).getGames_lost_as_black() + 1);

            userDao.findUser(mafia_2).setGames_lost_as_mafia(userDao.findUser(mafia_2).getGames_lost_as_mafia() + 1);
            userDao.findUser(mafia_2).setGames_lost(userDao.findUser(mafia_2).getGames_lost() + 1);
            userDao.findUser(mafia_2).setGames_played(userDao.findUser(mafia_2).getGames_played() + 1);
            userDao.findUser(mafia_2).setGames_lost_as_black(userDao.findUser(mafia_2).getGames_lost_as_black() + 1);

            userDao.findUser(don).setGames_lost_as_don(userDao.findUser(don).getGames_lost_as_don() + 1);
            userDao.findUser(don).setGames_lost(userDao.findUser(don).getGames_lost() + 1);
            userDao.findUser(don).setGames_played(userDao.findUser(don).getGames_played() + 1);
            userDao.findUser(don).setGames_lost_as_black(userDao.findUser(don).getGames_lost_as_black() + 1);

            userDao.findUser(sheriff).setGames_won_as_sheriff(userDao.findUser(sheriff).getGames_won_as_sheriff() + 1);
            userDao.findUser(sheriff).setGames_won(userDao.findUser(sheriff).getGames_won() + 1);
            userDao.findUser(sheriff).setGames_played(userDao.findUser(sheriff).getGames_played() + 1);
            userDao.findUser(sheriff).setGames_won_as_red(userDao.findUser(sheriff).getGames_won_as_red() + 1);
        } else {

            userDao.findUser(civilian_1).setGames_lost_as_civilian(userDao.findUser(civilian_1).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_1).setGames_lost(userDao.findUser(civilian_1).getGames_lost() + 1);
            userDao.findUser(civilian_1).setGames_played(userDao.findUser(civilian_1).getGames_played() + 1);
            userDao.findUser(civilian_1).setGames_lost_as_red(userDao.findUser(civilian_1).getGames_lost_as_red() + 1);

            userDao.findUser(civilian_2).setGames_lost_as_civilian(userDao.findUser(civilian_2).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_2).setGames_lost(userDao.findUser(civilian_2).getGames_lost() + 1);
            userDao.findUser(civilian_2).setGames_played(userDao.findUser(civilian_2).getGames_played() + 1);
            userDao.findUser(civilian_2).setGames_lost_as_red(userDao.findUser(civilian_2).getGames_lost_as_red() + 1);

            userDao.findUser(civilian_3).setGames_lost_as_civilian(userDao.findUser(civilian_3).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_3).setGames_lost(userDao.findUser(civilian_3).getGames_lost() + 1);
            userDao.findUser(civilian_3).setGames_played(userDao.findUser(civilian_3).getGames_played() + 1);
            userDao.findUser(civilian_3).setGames_lost_as_red(userDao.findUser(civilian_3).getGames_lost_as_red() + 1);

            userDao.findUser(civilian_4).setGames_lost_as_civilian(userDao.findUser(civilian_4).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_4).setGames_lost(userDao.findUser(civilian_4).getGames_lost() + 1);
            userDao.findUser(civilian_4).setGames_played(userDao.findUser(civilian_4).getGames_played() + 1);
            userDao.findUser(civilian_4).setGames_lost_as_red(userDao.findUser(civilian_4).getGames_lost_as_red() + 1);

            userDao.findUser(civilian_5).setGames_lost_as_civilian(userDao.findUser(civilian_5).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_5).setGames_lost(userDao.findUser(civilian_5).getGames_lost() + 1);
            userDao.findUser(civilian_5).setGames_played(userDao.findUser(civilian_5).getGames_played() + 1);
            userDao.findUser(civilian_5).setGames_lost_as_red(userDao.findUser(civilian_5).getGames_lost_as_red() + 1);

            userDao.findUser(civilian_6).setGames_lost_as_civilian(userDao.findUser(civilian_6).getGames_lost_as_civilian() + 1);
            userDao.findUser(civilian_6).setGames_lost(userDao.findUser(civilian_6).getGames_lost() + 1);
            userDao.findUser(civilian_6).setGames_played(userDao.findUser(civilian_6).getGames_played() + 1);
            userDao.findUser(civilian_6).setGames_lost_as_red(userDao.findUser(civilian_6).getGames_lost_as_red() + 1);

            userDao.findUser(mafia_1).setGames_won_as_mafia(userDao.findUser(mafia_1).getGames_won_as_mafia() + 1);
            userDao.findUser(mafia_1).setGames_won(userDao.findUser(mafia_1).getGames_won() + 1);
            userDao.findUser(mafia_1).setGames_played(userDao.findUser(mafia_1).getGames_played() + 1);
            userDao.findUser(mafia_1).setGames_won_as_black(userDao.findUser(mafia_1).getGames_won_as_black() + 1);

            userDao.findUser(mafia_2).setGames_won_as_mafia(userDao.findUser(mafia_2).getGames_won_as_mafia() + 1);
            userDao.findUser(mafia_2).setGames_won(userDao.findUser(mafia_2).getGames_won() + 1);
            userDao.findUser(mafia_2).setGames_played(userDao.findUser(mafia_2).getGames_played() + 1);
            userDao.findUser(mafia_2).setGames_won_as_black(userDao.findUser(mafia_2).getGames_won_as_black() + 1);

            userDao.findUser(don).setGames_won_as_don(userDao.findUser(don).getGames_won_as_don() + 1);
            userDao.findUser(don).setGames_won(userDao.findUser(don).getGames_won() + 1);
            userDao.findUser(don).setGames_played(userDao.findUser(don).getGames_played() + 1);
            userDao.findUser(don).setGames_won_as_black(userDao.findUser(don).getGames_won_as_black() + 1);

            userDao.findUser(sheriff).setGames_lost_as_sheriff(userDao.findUser(sheriff).getGames_lost_as_sheriff() + 1);
            userDao.findUser(sheriff).setGames_lost(userDao.findUser(sheriff).getGames_lost() + 1);
            userDao.findUser(sheriff).setGames_played(userDao.findUser(sheriff).getGames_played() + 1);
            userDao.findUser(sheriff).setGames_lost_as_red(userDao.findUser(sheriff).getGames_lost_as_red() + 1);

        }

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
