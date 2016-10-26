package ru.kpfu.itis.group11501.utkin.Services.Implementations;

import ru.kpfu.itis.group11501.utkin.Dao.Interfaces.TokenDao;
import ru.kpfu.itis.group11501.utkin.Dao.Implementations.TokenDaoImpl;
import ru.kpfu.itis.group11501.utkin.Services.Interfaces.TokenService;

/**
 * Created by user on 25.10.2016.
 */
public class TokenServiceImpl implements TokenService {
    TokenDao tokenDao = null;

    public TokenServiceImpl() {
        tokenDao = new TokenDaoImpl();
    }

    public void addToken(String id, String token) {
        tokenDao.addToken(id,token);
    }

    public void updateToken(String id, String token) {
        tokenDao.updateToken(id,token);
    }

    public void deleteToken(String token) {
        tokenDao.deleteToken(token);
    }
    //return id user
    public String findToken(String token) {
        return tokenDao.findToken(token);
    }
}
