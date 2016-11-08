package ru.kpfu.itis.group11501.utkin.Models;

/**
 * Created by user on 18.11.2016.
 */
public class Feed {
    private int id;
    private String text;
    private String header;

    public Feed(int id, String text, String header) {
        this.id = id;
        this.text = text;
        this.header = header;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
