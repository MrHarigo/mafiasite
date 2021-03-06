package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;
import ru.kpfu.itis.group11501.utkin.Models.*;
import ru.kpfu.itis.group11501.utkin.Services.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class CommentDaoImpl implements CommentDao {

    UserService userService;

    @Override
    public ArrayList<Comment> getCommentsForGame(Game game) {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comments WHERE game = ? order by comments.id desc";
            return selectRequestGetCommentsForGame(request, game);
        }
        return null;
    }

    private ArrayList<Comment> selectRequestGetCommentsForGame(String request, Game game) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,game.getId());
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                userService = new UserServiceImpl();
                User specialUser = userService.find(resultSet.getString("author"));
                comments.add(new Comment(resultSet.getInt("id"),specialUser,resultSet.getString("text"),game));
            }
            return comments;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Comment> getCommentsForFeed(Feed feed) {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comments WHERE feed = ? order by comments.id desc";
            return selectRequestGetCommentsForFeed(request, feed);
        }
        return null;
    }

    private ArrayList<Comment> selectRequestGetCommentsForFeed(String request, Feed feed) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,feed.getId());
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                userService = new UserServiceImpl();
                User specialUser = userService.find(resultSet.getString("author"));
                comments.add(new Comment(resultSet.getInt("id"),specialUser,feed, resultSet.getString("text")));
            }
            return comments;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Comment> getCommentsForTopic(Topic topic) {
        if (JDBConnection.getInstance().getConnection()!= null) {
            String request = "SELECT * FROM comments WHERE topic = ? order by comments.id desc";
            return selectRequestGetCommentsForTopic(request, topic);
        }
        return null;
    }

    private ArrayList<Comment> selectRequestGetCommentsForTopic(String request, Topic topic) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
            JDBConnection.statement.setInt(1,topic.getId());
            ResultSet resultSet = JDBConnection.statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                userService = new UserServiceImpl();
                User specialUser = userService.find(resultSet.getString("author"));
                comments.add(new Comment(resultSet.getInt("id"),specialUser,resultSet.getString("text"), topic));
            }
            return comments;
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCommentToGame(Comment comment) {
        if (JDBConnection.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comments (author,text,game) VALUES (?,?,?) ";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,comment.getAuthor().getNickname());
                JDBConnection.statement.setString(2,comment.getText());
                JDBConnection.statement.setInt(3,comment.getGame().getId());
                JDBConnection.getInstance().getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addCommentToFeed(Comment comment) {
        if (JDBConnection.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comments (author,text,feed) VALUES (?,?,?) ";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,comment.getAuthor().getNickname());
                JDBConnection.statement.setString(2,comment.getText());
                JDBConnection.statement.setInt(3,comment.getFeed().getId());
                JDBConnection.getInstance().getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void addCommentToTopic(Comment comment) {
        if (JDBConnection.getInstance().getConnection() != null && comment != null) {
            String request = "INSERT INTO comments (author,text,topic) VALUES (?,?,?) ";
            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);
                JDBConnection.statement.setString(1,comment.getAuthor().getNickname());
                JDBConnection.statement.setString(2,comment.getText());
                JDBConnection.statement.setInt(3,comment.getTopic().getId());
                JDBConnection.getInstance().getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
