package ru.kpfu.itis.group11501.utkin.Verifiers;

import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.UserDao;
import ru.kpfu.itis.group11501.utkin.Models.User;

/**
 * Created by user on 25.10.2016.
 */
public class UserVerify {
    public static User checkUserInBD(UserDao userDao, String login) {
        return userDao.findUser(login);
    }
}