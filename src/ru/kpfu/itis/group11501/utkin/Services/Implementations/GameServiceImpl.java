package ru.kpfu.itis.group11501.utkin.Services.Implementations;

import ru.kpfu.itis.group11501.utkin.Dao.Implementations.GameDaoImpl;
import ru.kpfu.itis.group11501.utkin.Errors.Error;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.GameService;

import java.sql.Date;
import java.time.LocalDateTime;
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

    @Override
    public ArrayList<Game> getAllGames() {
        error = null;
        if (gameDao.getAllGames()==null) {
            error = new Error("games_not_found", "Games are not found!");
            return null;
        } else {
            return gameDao.getAllGames();
        }
    }

    @Override
    public Game createGame(String player_civilian_1, String player_civilian_2, String player_civilian_3,
                                String player_civilian_4, String player_civilian_5, String player_civilian_6,
                                String player_mafia_1, String player_mafia_2, String player_don, String player_sheriff,
                                Date date, String winner_team) {
        return  gameDao.createGame(player_civilian_1, player_civilian_2, player_civilian_3, player_civilian_4,
                player_civilian_5, player_civilian_6, player_mafia_1, player_mafia_2,player_don, player_sheriff,
                date,winner_team);
    }

    @Override
    public ArrayList<Game> sortGames(String parameter, ArrayList<Game> games) {
        switch (parameter) {
            case "month":
                ArrayList<Game> newGames = new ArrayList<>();
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    java.util.Date date = game.getDate();
                    int year = Integer.parseInt(date.toString().substring(0,4));
                    int month = Integer.parseInt(date.toString().substring(5,7));

                    if ((month == LocalDateTime.now().getMonthValue())&&
                            (year == LocalDateTime.now().getYear())) {
                        newGames.add(game);
                    }
                }
                return newGames;
            case "year":
                ArrayList<Game> newGames2 = new ArrayList<>();
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    java.util.Date date = game.getDate();
                    int year = Integer.parseInt(date.toString().substring(0,4));
                    if (year == LocalDateTime.now().getYear()) {
                        newGames2.add(game);
                    }
                }
                return newGames2;
            default: return new ArrayList<>();
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
