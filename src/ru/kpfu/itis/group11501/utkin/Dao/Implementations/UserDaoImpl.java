package ru.kpfu.itis.group11501.utkin.Dao.Implementations;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.UserDao;
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

    @Override
    public void updateUser(User user) {
        String nickname = user.getNickname();
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "update users set games_played = ?, games_won = ?, games_lost = ?, games_won_as_red  = ?," +
                    " games_lost_as_red = ?, games_won_as_black = ?, games_lost_as_black = ?, games_won_as_don = ?," +
                    " games_lost_as_don = ?, games_won_as_sheriff = ?, games_lost_as_sheriff = ?, games_won_as_civilian = ?," +
                    " games_lost_as_civilian = ?, games_won_as_mafia = ?, games_lost_as_mafia = ? where nickname = ?";
            updateUserRequest(request,user);
        }
    }

    @Override
    public ArrayList<User> getUsersForNextMafia() {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM users WHERE is_at_evening = ?";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setBoolean(1,true);
                ResultSet resultSet = JDBConnection.statement.executeQuery();
                ArrayList<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getString("nickname"),resultSet.getString("password"), resultSet.getString("photo"), resultSet.getInt("experience"),
                            resultSet.getInt("games_played"), resultSet.getInt("games_won"), resultSet.getInt("games_lost"),
                            resultSet.getInt("games_won_as_red"), resultSet.getInt("games_lost_as_red"), resultSet.getInt("games_won_as_black"),
                            resultSet.getInt("games_lost_as_black"), resultSet.getInt("games_won_as_don"), resultSet.getInt("games_lost_as_don"),
                            resultSet.getInt("games_won_as_sheriff"), resultSet.getInt("games_lost_as_sheriff"), resultSet.getInt("games_won_as_civilian"),
                            resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia"),
                            resultSet.getBoolean("is_admin"), resultSet.getBoolean("is_at_evening")));
                }
                return users;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<User> searchUsersLike(String pattern) {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * from users WHERE nickname LIKE ?";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,pattern + "%");
                ResultSet resultSet = JDBConnection.statement.executeQuery();
                ArrayList<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getString("nickname"),resultSet.getString("password"), resultSet.getString("photo"), resultSet.getInt("experience"),
                            resultSet.getInt("games_played"), resultSet.getInt("games_won"), resultSet.getInt("games_lost"),
                            resultSet.getInt("games_won_as_red"), resultSet.getInt("games_lost_as_red"), resultSet.getInt("games_won_as_black"),
                            resultSet.getInt("games_lost_as_black"), resultSet.getInt("games_won_as_don"), resultSet.getInt("games_lost_as_don"),
                            resultSet.getInt("games_won_as_sheriff"), resultSet.getInt("games_lost_as_sheriff"), resultSet.getInt("games_won_as_civilian"),
                            resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia"),
                            resultSet.getBoolean("is_admin"), resultSet.getBoolean("is_at_evening")));
                }
                return users;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
            return null;
        }
        return null;
    }

    @Override
    public void deleteUser(String nickname) {
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "delete from users where nickname = ?";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,nickname);
                JDBConnection.statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUserInfo(String oldNickname, String newNickname, String password, int experience) {
        if (JDBConnection.getInstance().getConnection()!= null && !newNickname.equals("")) {
            String request = "update users set nickname = ?, password = ?, experience = ? where nickname = ?";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,newNickname);
                JDBConnection.statement.setString(2,password);
                JDBConnection.statement.setInt(3,experience);
                JDBConnection.statement.setString(4,oldNickname);
                JDBConnection.statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void addUserToNextMafia(User user) {
        String nickname = user.getNickname();
        if (JDBConnection.getInstance().getConnection()!= null && !nickname.equals("")) {
            String request = "update users set is_at_evening = ? where nickname = ?";
            updateNextMafia(request,nickname);
        }
    }


    private void updateNextMafia(String request, String nickname) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setBoolean(1,true);
            JDBConnection.statement.setString(2,nickname);
            JDBConnection.statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateUserRequest(String request, User user) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,user.getGames_played());
            JDBConnection.statement.setInt(2,user.getGames_won());
            JDBConnection.statement.setInt(3,user.getGames_lost());
            JDBConnection.statement.setInt(4,user.getGames_won_as_red());
            JDBConnection.statement.setInt(5,user.getGames_lost_as_red());
            JDBConnection.statement.setInt(6,user.getGames_won_as_black());
            JDBConnection.statement.setInt(7,user.getGames_lost_as_black());
            JDBConnection.statement.setInt(8,user.getGames_won_as_don());
            JDBConnection.statement.setInt(9,user.getGames_lost_as_don());
            JDBConnection.statement.setInt(10,user.getGames_won_as_sheriff());
            JDBConnection.statement.setInt(11,user.getGames_lost_as_sheriff());
            JDBConnection.statement.setInt(12,user.getGames_won_as_civilian());
            JDBConnection.statement.setInt(13,user.getGames_lost_as_civilian());
            JDBConnection.statement.setInt(14,user.getGames_won_as_mafia());
            JDBConnection.statement.setInt(15,user.getGames_lost_as_mafia());
            JDBConnection.statement.setString(16,user.getNickname());
            JDBConnection.statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia"),
                        resultSet.getBoolean("is_admin"), resultSet.getBoolean("is_at_evening")));
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
                        resultSet.getInt("games_lost_as_civilian"), resultSet.getInt("games_won_as_mafia"), resultSet.getInt("games_lost_as_mafia"),
                        resultSet.getBoolean("is_admin"), resultSet.getBoolean("is_at_evening"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }
}
