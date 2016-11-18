package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by user on 25.10.2016.
 */
public class UserDaoImpl implements UserDao {
    public void addUser(User user) {
        if (JDBConnection.getInstance().getConnection() != null && user != null) {
            String request = "INSERT INTO users (nickname, password, photo, experience) VALUES (?,?,?,?) ";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,""+user.getNickname());
                JDBConnection.statement.setString(2,user.getPassword());
                JDBConnection.statement.setString(3,user.getPhoto());
                JDBConnection.statement.setInt(4,user.getExperience());
                JDBConnection.getInstance().getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User findUser(String nickname) {
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "SELECT * FROM users WHERE nickname= ? ";
            return selectRequestFindUser(request, nickname);
        }
        return null;
    }

    @Override
    public void setAvatar(String filepath, User user) {
        String nickname = user.getNickname();
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "update users set photo = ? where nickname = ?";
            updatePhotoRequest(request,filepath,nickname);
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM users";
            return selectRequestGetAllUsers(request);
        }
        return null;
    }

    private ArrayList<User> selectRequestGetAllUsers(String request) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("nickname"),resultSet.getString("password"), resultSet.getString("photo"), resultSet.getInt("experience"),
                        resultSet.getInt("games_played"), resultSet.getInt("games_won"), resultSet.getInt("games_lost"),
                        resultSet.getInt("games_won_as_red"), resultSet.getInt("games_lost_as_red"), resultSet.getInt("games_won_as_black"),
                        resultSet.getInt("games_lost_as_black"), resultSet.getInt("games_won_as_don"), resultSet.getInt("games_lost_as_don"),
                        resultSet.getInt("games_won_as_sheriff"), resultSet.getInt("games_lost_as_sheriff"), resultSet.getInt("games_won_as_civilian"),
                        resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia")));
            }
            return users;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    public void updatePhotoRequest(String request,String filepath, String nickname) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setString(1,filepath);
            JDBConnection.statement.setString(2,nickname);
            JDBConnection.statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User selectRequestFindUser(String request, String param) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setString(1,param);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getString("nickname"),resultSet.getString("password"), resultSet.getString("photo"), resultSet.getInt("experience"),
                        resultSet.getInt("games_played"), resultSet.getInt("games_won"), resultSet.getInt("games_lost"),
                        resultSet.getInt("games_won_as_red"), resultSet.getInt("games_lost_as_red"), resultSet.getInt("games_won_as_black"),
                        resultSet.getInt("games_lost_as_black"), resultSet.getInt("games_won_as_don"), resultSet.getInt("games_lost_as_don"),
                        resultSet.getInt("games_won_as_sheriff"), resultSet.getInt("games_lost_as_sheriff"), resultSet.getInt("games_won_as_civilian"),
                        resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }
}
