package ru.kpfu.itis.group11501.utkin.Services;

/**
 * Created by user on 25.10.2016.
 */
public interface TokenService {
    void addToken(String id, String token);
    void updateToken(String id, String token);
    void deleteToken(String token);
    String findToken(String token);
}
