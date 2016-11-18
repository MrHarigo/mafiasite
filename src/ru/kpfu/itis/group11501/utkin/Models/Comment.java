package ru.kpfu.itis.group11501.utkin.Models;

/**
 * Created by user on 18.11.2016.
 */
public class Comment {
    private int id;
    private User author;
    private Feed feed;
    private String text;
    private Game game;
    private Topic topic;

    public Comment(User author, String text, Topic topic) {
        this.author = author;
        this.text = text;
        this.topic = topic;
    }

    public Comment(User author, Feed feed, String text) {
        this.author = author;
        this.feed = feed;
        this.text = text;
    }

    public Comment(User author, String text, Game game) {
        this.author = author;
        this.text = text;
        this.game = game;
    }

    public Comment(int id, User author, Feed feed, String text) {
        this.id = id;
        this.author = author;
        this.feed = feed;
        this.text = text;
    }

    public Comment(int id, User author, String text, Game game) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.game = game;
    }

    public Comment(int id, User author, String text, Topic topic) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
