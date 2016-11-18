package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface GameDao {
    void addGame(Game game);
    Game findGame(int id);
    ArrayList<Game> findGamesWithUser(User user);
}
