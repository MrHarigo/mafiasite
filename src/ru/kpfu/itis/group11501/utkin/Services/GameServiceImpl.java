package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Dao.GameDaoImpl;
import ru.kpfu.itis.group11501.utkin.Errors.Error;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class GameServiceImpl implements GameService {

    private GameDaoImpl gameDao = null;
    private Error error = null;

    public GameServiceImpl() {
        this.gameDao = new GameDaoImpl();
    }

    @Override
    public Game findGame(int id) {
        error = null;
        if (gameDao.findGame(id)==null) {
            error = new Error("game_not_found", "Game is not found!");
            return null;
        } else {
            return gameDao.findGame(id);
        }
    }

    @Override
    public ArrayList<Game> findGamesWithUser(User user) {
        error = null;
        if (gameDao.findGamesWithUser(user)==null) {
            error = new Error("games_not_found", "Games are not found!");
            return null;
        } else {
            return gameDao.findGamesWithUser(user);
        }
    }

    public GameDaoImpl getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDaoImpl gameDao) {
        this.gameDao = gameDao;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
