package ru.kpfu.itis.group11501.utkin.Utils;

import java.security.SecureRandom;

/**
 * Created by user on 21.10.2016.
 */
public class Token {
    private String string = null;

    public static String getToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
