package ru.kpfu.itis.group11501.utkin.Services.Interfaces;

import ru.kpfu.itis.group11501.utkin.Models.Topic;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public interface TopicService {
    Topic findTopic(int id);
    ArrayList<Topic> getAllTopics();
    void addTopic(Topic topic);
}
