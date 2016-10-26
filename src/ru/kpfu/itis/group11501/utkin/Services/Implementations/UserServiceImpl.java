package ru.kpfu.itis.group11501.utkin.Services.Implementations;

import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.UserDao;
import ru.kpfu.itis.group11501.utkin.Dao.Implementations.UserDaoImpl;
import ru.kpfu.itis.group11501.utkin.Helpers.BubbleSort;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.UserService;
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
                User newUser;
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
        if (nickname != null) {
            userDao.deleteUser(nickname);
        }
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

        User user_civilian_1 =  userDao.findUser(civilian_1);
        User user_civilian_2 =  userDao.findUser(civilian_2);
        User user_civilian_3 =  userDao.findUser(civilian_3);
        User user_civilian_4 =  userDao.findUser(civilian_4);
        User user_civilian_5 =  userDao.findUser(civilian_5);
        User user_civilian_6 =  userDao.findUser(civilian_6);
        User user_mafia_1 =  userDao.findUser(mafia_1);
        User user_mafia_2 =  userDao.findUser(mafia_2);
        User user_don =  userDao.findUser(don);
        User user_sheriff =  userDao.findUser(sheriff);

        if (winner_team.equals("red")) {

            user_civilian_1.setGames_won_as_civilian(user_civilian_1.getGames_won_as_civilian() + 1);
            user_civilian_1.setGames_won(user_civilian_1.getGames_won() + 1);
            user_civilian_1.setGames_played(user_civilian_1.getGames_played() + 1);
            user_civilian_1.setGames_won_as_red(user_civilian_1.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_1);

            user_civilian_2.setGames_won_as_civilian(user_civilian_2.getGames_won_as_civilian() + 1);
            user_civilian_2.setGames_won(user_civilian_2.getGames_won() + 1);
            user_civilian_2.setGames_played(user_civilian_2.getGames_played() + 1);
            user_civilian_2.setGames_won_as_red(user_civilian_2.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_2);

            user_civilian_3.setGames_won_as_civilian(user_civilian_3.getGames_won_as_civilian() + 1);
            user_civilian_3.setGames_won(user_civilian_3.getGames_won() + 1);
            user_civilian_3.setGames_played(user_civilian_3.getGames_played() + 1);
            user_civilian_3.setGames_won_as_red(user_civilian_3.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_3);

            user_civilian_4.setGames_won_as_civilian(user_civilian_4.getGames_won_as_civilian() + 1);
            user_civilian_4.setGames_won(user_civilian_4.getGames_won() + 1);
            user_civilian_4.setGames_played(user_civilian_4.getGames_played() + 1);
            user_civilian_4.setGames_won_as_red(user_civilian_4.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_4);

            user_civilian_5.setGames_won_as_civilian(user_civilian_5.getGames_won_as_civilian() + 1);
            user_civilian_5.setGames_won(user_civilian_5.getGames_won() + 1);
            user_civilian_5.setGames_played(user_civilian_5.getGames_played() + 1);
            user_civilian_5.setGames_won_as_red(user_civilian_5.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_5);

            user_civilian_6.setGames_won_as_civilian(user_civilian_6.getGames_won_as_civilian() + 1);
            user_civilian_6.setGames_won(user_civilian_6.getGames_won() + 1);
            user_civilian_6.setGames_played(user_civilian_6.getGames_played() + 1);
            user_civilian_6.setGames_won_as_red(user_civilian_6.getGames_won_as_red() + 1);
            userDao.updateUser(user_civilian_6);

            user_mafia_1.setGames_lost_as_mafia(user_mafia_1.getGames_lost_as_mafia() + 1);
            user_mafia_1.setGames_lost(user_mafia_1.getGames_lost() + 1);
            user_mafia_1.setGames_played(user_mafia_1.getGames_played() + 1);
            user_mafia_1.setGames_lost_as_black(user_mafia_1.getGames_lost_as_black() + 1);
            userDao.updateUser(user_mafia_1);

            user_mafia_2.setGames_lost_as_mafia(user_mafia_2.getGames_lost_as_mafia() + 1);
            user_mafia_2.setGames_lost(user_mafia_2.getGames_lost() + 1);
            user_mafia_2.setGames_played(user_mafia_2.getGames_played() + 1);
            user_mafia_2.setGames_lost_as_black(user_mafia_2.getGames_lost_as_black() + 1);
            userDao.updateUser(user_mafia_2);

            user_don.setGames_lost_as_don(user_don.getGames_lost_as_don() + 1);
            user_don.setGames_lost(user_don.getGames_lost() + 1);
            user_don.setGames_played(user_don.getGames_played() + 1);
            user_don.setGames_lost_as_black(user_don.getGames_lost_as_black() + 1);
            userDao.updateUser(user_don);

            user_sheriff.setGames_won_as_sheriff(user_sheriff.getGames_won_as_sheriff() + 1);
            user_sheriff.setGames_won(user_sheriff.getGames_won() + 1);
            user_sheriff.setGames_played(user_sheriff.getGames_played() + 1);
            user_sheriff.setGames_won_as_red(user_sheriff.getGames_won_as_red() + 1);
            userDao.updateUser(user_sheriff);

        } else {

            user_civilian_1.setGames_lost_as_civilian(user_civilian_1.getGames_lost_as_civilian() + 1);
            user_civilian_1.setGames_lost(user_civilian_1.getGames_lost() + 1);
            user_civilian_1.setGames_played(user_civilian_1.getGames_played() + 1);
            user_civilian_1.setGames_lost_as_red(user_civilian_1.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_1);

            user_civilian_2.setGames_lost_as_civilian(user_civilian_2.getGames_lost_as_civilian() + 1);
            user_civilian_2.setGames_lost(user_civilian_2.getGames_lost() + 1);
            user_civilian_2.setGames_played(user_civilian_2.getGames_played() + 1);
            user_civilian_2.setGames_lost_as_red(user_civilian_2.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_2);

            user_civilian_3.setGames_lost_as_civilian(user_civilian_3.getGames_lost_as_civilian() + 1);
            user_civilian_3.setGames_lost(user_civilian_3.getGames_lost() + 1);
            user_civilian_3.setGames_played(user_civilian_3.getGames_played() + 1);
            user_civilian_3.setGames_lost_as_red(user_civilian_3.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_3);

            user_civilian_4.setGames_lost_as_civilian(user_civilian_4.getGames_lost_as_civilian() + 1);
            user_civilian_4.setGames_lost(user_civilian_4.getGames_lost() + 1);
            user_civilian_4.setGames_played(user_civilian_4.getGames_played() + 1);
            user_civilian_4.setGames_lost_as_red(user_civilian_4.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_4);

            user_civilian_5.setGames_lost_as_civilian(user_civilian_5.getGames_lost_as_civilian() + 1);
            user_civilian_5.setGames_lost(user_civilian_5.getGames_lost() + 1);
            user_civilian_5.setGames_played(user_civilian_5.getGames_played() + 1);
            user_civilian_5.setGames_lost_as_red(user_civilian_5.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_5);

            user_civilian_6.setGames_lost_as_civilian(user_civilian_6.getGames_lost_as_civilian() + 1);
            user_civilian_6.setGames_lost(user_civilian_6.getGames_lost() + 1);
            user_civilian_6.setGames_played(user_civilian_6.getGames_played() + 1);
            user_civilian_6.setGames_lost_as_red(user_civilian_6.getGames_lost_as_red() + 1);
            userDao.updateUser(user_civilian_6);

            user_mafia_1.setGames_won_as_mafia(user_mafia_1.getGames_won_as_mafia() + 1);
            user_mafia_1.setGames_won(user_mafia_1.getGames_won() + 1);
            user_mafia_1.setGames_played(user_mafia_1.getGames_played() + 1);
            user_mafia_1.setGames_won_as_black(user_mafia_1.getGames_won_as_black() + 1);
            userDao.updateUser(user_mafia_1);

            user_mafia_2.setGames_won_as_mafia(user_mafia_2.getGames_won_as_mafia() + 1);
            user_mafia_2.setGames_won(user_mafia_2.getGames_won() + 1);
            user_mafia_2.setGames_played(user_mafia_2.getGames_played() + 1);
            user_mafia_2.setGames_won_as_black(user_mafia_2.getGames_won_as_black() + 1);
            userDao.updateUser(user_mafia_2);

            user_don.setGames_won_as_don(user_don.getGames_won_as_don() + 1);
            user_don.setGames_won(user_don.getGames_won() + 1);
            user_don.setGames_played(user_don.getGames_played() + 1);
            user_don.setGames_won_as_black(user_don.getGames_won_as_black() + 1);
            userDao.updateUser(user_don);

            user_sheriff.setGames_lost_as_sheriff(user_sheriff.getGames_lost_as_sheriff() + 1);
            user_sheriff.setGames_lost(user_sheriff.getGames_lost() + 1);
            user_sheriff.setGames_played(user_sheriff.getGames_played() + 1);
            user_sheriff.setGames_lost_as_red(user_sheriff.getGames_lost_as_red() + 1);
            userDao.updateUser(user_sheriff);

        }

    }

    @Override
    public ArrayList<User> sortUsers(String parameter, ArrayList<User> users) {
        switch (parameter) {
            case "games":
                BubbleSort.sortUsersByGames(users);
                return users;
            case "wins":
                BubbleSort.sortUsersByWins(users);
                return users;
            case "red":
                BubbleSort.sortUsersByRedWins(users);
                return users;
            case "black":
                BubbleSort.sortUsersByBlackWins(users);
                return users;
            case "nickname":
                BubbleSort.sortUsersByNickname(users);
                return users;
            case "experience":
                BubbleSort.sortUsersByExperience(users);
                return users;
            default: return new ArrayList<>();
        }
    }

    @Override
    public void addUserToNextMafia(User user) {
        error = null;
        if (user != null) {
            if (!user.is_at_evening())
                userDao.addUserToNextMafia(user);
            else
                error = new Error("Already_in_next_mafia", "You have already registered for next mafia!");
        } else
            error = new Error("user_not_found", "User not found");
    }

    @Override
    public ArrayList<User> getUsersForNextMafia() {
        return userDao.getUsersForNextMafia();
    }

    @Override
    public ArrayList<User> searchUsersLike(String pattern) {
        return userDao.searchUsersLike(pattern);
    }

    @Override
    public void updateUserInfo(String oldNickname, String newNickname, String password, int experience) {
        if (UserVerify.checkUserInBD(userDao, newNickname) == null) {
            password = Hash.getMd5Apache(password);
            userDao.updateUserInfo(oldNickname, newNickname, password, experience);
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
