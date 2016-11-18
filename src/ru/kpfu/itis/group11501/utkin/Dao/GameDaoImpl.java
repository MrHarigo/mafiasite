package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Models.Game;
import ru.kpfu.itis.group11501.utkin.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class GameDaoImpl implements GameDao {
    @Override
    public void addGame(Game game) {

    }

    @Override
    public Game findGame(int id) {
        if (JDBConnection.getInstance().getConnection()!= null && id > 0) {
            String request = "SELECT * FROM games WHERE id= ? ";
            return selectRequestFindGame(request, id);
        }
        return null;
    }

    public Game selectRequestFindGame(String request, int param) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,param);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            while (resultSet.next()) {
                return new Game(resultSet.getInt("id"),resultSet.getString("player_civilian_1"),resultSet.getString("player_civilian_2"),
                        resultSet.getString("player_civilian_3"), resultSet.getString("player_civilian_4"), resultSet.getString("player_civilian_5"),
                        resultSet.getString("player_civilian_6"), resultSet.getString("player_mafia_1"), resultSet.getString("player_mafia_2"),
                        resultSet.getString("player_don"), resultSet.getString("player_sheriff"), resultSet.getDate("date"), resultSet.getString("winner_team"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Game> findGamesWithUser(User user) {
        String nickname = user.getNickname();
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "SELECT * FROM games WHERE ((player_civilian_1 = ?) or (player_civilian_2 = ?)" +
                    " or (player_civilian_3 = ?) or (player_civilian_4 = ?) or (player_civilian_5 = ?) " +
                    "or (player_civilian_6 = ?) or (player_mafia_1 = ?) or (player_mafia_2 = ?) " +
                    "or (player_don = ?) or (player_sheriff = ?)) order by games.id desc";
            return selectRequestFindGamesWithUser(request, nickname);
        }
        return null;
    }

    @Override
    public ArrayList<Game> getAllGames() {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM games order by games.id desc";
            return selectRequestGetAllGames(request);
        }
        return null;
    }

    private ArrayList<Game> selectRequestGetAllGames(String request) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Game> games = new ArrayList<>();
            while (resultSet.next()) {
                games.add(new Game(resultSet.getInt("id"),resultSet.getString("player_civilian_1"),resultSet.getString("player_civilian_2"),
                        resultSet.getString("player_civilian_3"), resultSet.getString("player_civilian_4"), resultSet.getString("player_civilian_5"),
                        resultSet.getString("player_civilian_6"), resultSet.getString("player_mafia_1"), resultSet.getString("player_mafia_2"),
                        resultSet.getString("player_don"), resultSet.getString("player_sheriff"), resultSet.getDate("date"), resultSet.getString("winner_team")));
            }
            return games;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    private ArrayList<Game> selectRequestFindGamesWithUser(String request, String nickname) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setString(1,nickname);
            JDBConnection.statement.setString(2,nickname);
            JDBConnection.statement.setString(3,nickname);
            JDBConnection.statement.setString(4,nickname);
            JDBConnection.statement.setString(5,nickname);
            JDBConnection.statement.setString(6,nickname);
            JDBConnection.statement.setString(7,nickname);
            JDBConnection.statement.setString(8,nickname);
            JDBConnection.statement.setString(9,nickname);
            JDBConnection.statement.setString(10,nickname);
            ResultSet resultSet = JDBConnection.statement.executeQuery();

            ArrayList<Game> games = new ArrayList<>();
            while (resultSet.next()) {
                games.add(new Game(resultSet.getInt("id"),resultSet.getString("player_civilian_1"),resultSet.getString("player_civilian_2"),
                        resultSet.getString("player_civilian_3"), resultSet.getString("player_civilian_4"), resultSet.getString("player_civilian_5"),
                        resultSet.getString("player_civilian_6"), resultSet.getString("player_mafia_1"), resultSet.getString("player_mafia_2"),
                        resultSet.getString("player_don"), resultSet.getString("player_sheriff"), resultSet.getDate("date"), resultSet.getString("winner_team")));
            }
            return games;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }
}
