package ru.kpfu.itis.group11501.utkin.Dao;

import ru.kpfu.itis.group11501.utkin.Models.Topic;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface TopicDao {
    Topic findTopic(int id);
    ArrayList<Topic> getAllTopics();
    void addTopic(Topic topic);
}
