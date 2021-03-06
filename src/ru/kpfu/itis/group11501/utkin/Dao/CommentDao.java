package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Models.*;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface CommentDao {
    ArrayList<Comment> getCommentsForGame(Game game);
    ArrayList<Comment> getCommentsForFeed(Feed feed);
    ArrayList<Comment> getCommentsForTopic(Topic topic);
    void addCommentToGame(Comment comment);
    void addCommentToFeed(Comment comment);
    void addCommentToTopic(Comment comment);
}
