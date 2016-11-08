package ru.kpfu.itis.group11501.utkin.Dao.Implementations;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.TopicDao;
import ru.kpfu.itis.group11501.utkin.Models.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class TopicDaoImpl implements TopicDao {
    @Override
    public Topic findTopic(int id) {
        if (JDBConnection.getInstance().getConnection()!= null && id > 0) {
            String request = "SELECT * FROM topics WHERE id= ? ";
            return selectRequestFindTopic(request, id);
        }
        return null;
    }

    private Topic selectRequestFindTopic(String request, int id) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,id);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            while (resultSet.next()) {
                return new Topic(resultSet.getInt("id"),resultSet.getString("text"),resultSet.getString("header"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Topic> getAllTopics() {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM topics order by topics.id desc";
            return selectRequestGetAllTopics(request);
        }
        return null;
    }

    private ArrayList<Topic> selectRequestGetAllTopics(String request) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Topic> topics = new ArrayList<>();
            while (resultSet.next()) {
                topics.add(new Topic(resultSet.getInt("id"),resultSet.getString("text"),resultSet.getString("header")));
            }
            return topics;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public void addTopic(Topic topic) {

    }
}
