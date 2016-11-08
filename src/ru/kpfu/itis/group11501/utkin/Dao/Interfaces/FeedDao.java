package ru.kpfu.itis.group11501.utkin.Dao.Interfaces;

import ru.kpfu.itis.group11501.utkin.Models.Feed;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface FeedDao {
    Feed findFeed(int id);
    void addFeed(Feed feed);
    ArrayList<Feed> get3LastFeed();
}
