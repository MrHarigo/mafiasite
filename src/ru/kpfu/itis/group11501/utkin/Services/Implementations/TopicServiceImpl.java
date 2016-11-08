package ru.kpfu.itis.group11501.utkin.Services.Implementations;

import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.TopicDao;
import ru.kpfu.itis.group11501.utkin.Dao.Implementations.TopicDaoImpl;
import ru.kpfu.itis.group11501.utkin.Errors.Error;
import ru.kpfu.itis.group11501.utkin.Models.Topic;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TopicService;

import java.util.ArrayList;

/**
 * Created by user on 18.11.2016.
 */
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao = null;
    private Error error = null;

    public TopicServiceImpl() {
        this.topicDao = new TopicDaoImpl();
    }

    @Override
    public Topic findTopic(int id) {
        error = null;
        if (topicDao.findTopic(id)==null) {
            error = new Error("topic_not_found", "topic is not found!");
            return null;
        } else {
            return topicDao.findTopic(id);
        }
    }

    @Override
    public ArrayList<Topic> getAllTopics() {
        error = null;
        if (topicDao.getAllTopics()==null) {
            error = new Error("topics_not_found", "topics are not found!");
            return null;
        } else {
            return topicDao.getAllTopics();
        }
    }

    @Override
    public void addTopic(Topic topic) {

    }
}
