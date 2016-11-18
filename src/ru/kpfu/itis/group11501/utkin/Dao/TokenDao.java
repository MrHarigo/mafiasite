package ru.kpfu.itis.group11501.utkin.Dao;

/**
 * Created by user on 21.10.2016.
 */
public interface TokenDao {
    void addToken(String id, String token);
    void updateToken(String id, String token);
    void deleteToken(String token);
    String findToken(String token);
}
