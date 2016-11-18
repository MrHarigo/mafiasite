package ru.kpfu.itis.group11501.utkin.Services;

import ru.kpfu.itis.group11501.utkin.Dao.FeedDaoImpl;
import ru.kpfu.itis.group11501.utkin.Errors.Error;
import ru.kpfu.itis.group11501.utkin.Models.Feed;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class FeedServiceImpl implements FeedService {
    private FeedDaoImpl feedDao = null;
    private Error error = null;

    public FeedServiceImpl() {
        this.feedDao = new FeedDaoImpl();
    }

    @Override
    public Feed findFeed(int id) {
        error = null;
        if (feedDao.findFeed(id) == null) {
            error = new Error("no_feed_found", "There is no such feed");
            return null;
        } else
            return feedDao.findFeed(id);
    }

    @Override
    public void addFeed(Feed feed) {

    }

    @Override
    public ArrayList<Feed> get3LastFeed() {
        error = null;
        if (feedDao.get3LastFeed() == null) {
            error = new Error("no_feed_found", "There is no such feed");
            return null;
        } else
            return feedDao.get3LastFeed();
    }
}
