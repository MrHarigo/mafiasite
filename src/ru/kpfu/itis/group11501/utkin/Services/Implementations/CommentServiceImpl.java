package ru.kpfu.itis.group11501.utkin.Services.Implementations;

import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.CommentDao;
import ru.kpfu.itis.group11501.utkin.Dao.Implementations.CommentDaoImpl;
import ru.kpfu.itis.group11501.utkin.Errors.Error;
import ru.kpfu.itis.group11501.utkin.Models.*;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.CommentService;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class CommentServiceImpl implements CommentService {

    CommentDao commentDao;
    Error error;

    public CommentServiceImpl() {
        this.commentDao = new CommentDaoImpl();
    }

    @Override
    public ArrayList<Comment> getCommentsForGame(Game game) {
        error = null;
        if (commentDao.getCommentsForGame(game) == null) {
            error = new Error("no_comments", "no comments for this game!");
            return null;
        } else
            return commentDao.getCommentsForGame(game);
    }

    @Override
    public ArrayList<Comment> getCommentsForFeed(Feed feed) {
        error = null;
        if (commentDao.getCommentsForFeed(feed) == null) {
            error = new Error("no_comments", "no comments for this feed!");
            return null;
        } else
            return commentDao.getCommentsForFeed(feed);
    }

    @Override
    public ArrayList<Comment> getCommentsForTopic(Topic topic) {
        error = null;
        if (commentDao.getCommentsForTopic(topic) == null) {
            error = new Error("no_comments", "no comments for this topic!");
            return null;
        } else
            return commentDao.getCommentsForTopic(topic);
    }

    @Override
    public void addCommentToGame(Comment comment) {
        commentDao.addCommentToGame(comment);
    }

    @Override
    public void addCommentToFeed(Comment comment) {
        commentDao.addCommentToFeed(comment);
    }

    @Override
    public void addCommentToTopic(Comment comment) {
        commentDao.addCommentToTopic(comment);
    }

    @Override
    public ArrayList<Comment> getAllComments() {
        if (commentDao.getAllComments() != null) {
            return commentDao.getAllComments();
        }
        return null;
    }
}
