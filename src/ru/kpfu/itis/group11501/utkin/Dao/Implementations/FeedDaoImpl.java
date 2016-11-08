package ru.kpfu.itis.group11501.utkin.Dao.Implementations;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.FeedDao;
import ru.kpfu.itis.group11501.utkin.Models.Feed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class FeedDaoImpl implements FeedDao {
    @Override
    public Feed findFeed(int id) {
        if (JDBConnection.getInstance().getConnection()!= null && id > 0) {
            String request = "SELECT * FROM feeds WHERE id= ? ";
            return selectRequestFindFeed(request, id);
        }
        return null;
    }

    private Feed selectRequestFindFeed(String request, int id) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,id);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            while (resultSet.next()) {
                return new Feed(resultSet.getInt("id"),resultSet.getString("text"),resultSet.getString("header"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public void addFeed(Feed feed) {

    }

    @Override
    public ArrayList<Feed> get3LastFeed() {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM feeds order by feeds.id desc";
            return selectRequestGet3LastFeed(request);
        }
        return null;
    }

    private ArrayList<Feed> selectRequestGet3LastFeed(String request) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Feed> feeds = new ArrayList<>();
            int count = 0;
            while (resultSet.next() && count < 3) {
                feeds.add(new Feed(resultSet.getInt("id"),resultSet.getString("text"),resultSet.getString("header")));
                count++;
            }
            return feeds;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }
}
