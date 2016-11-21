package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface GameService {
    Game findGame(int id);
    ArrayList<Game> findGamesWithUser(User user);
    ArrayList<Game> getAllGames();
    Game createGame(String player_civilian_1, String player_civilian_2, String player_civilian_3,
                    String player_civilian_4, String player_civilian_5, String player_civilian_6,
                    String player_mafia_1, String player_mafia_2, String player_don, String player_sheriff,
                    Date date, String winner_team);
}
