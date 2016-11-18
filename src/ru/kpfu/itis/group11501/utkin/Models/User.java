package ru.kpfu.itis.group11501.utkin.Models;

/**
 * Created by user on 21.10.2016.
 */
public class User {
    private String nickname;
    private String password;
    private String photo;
    private int experience;
    private int games_played;
    private int games_won;
    private int games_lost;
    private int games_won_as_red;
    private int games_lost_as_red;
    private int games_won_as_black;
    private int games_lost_as_black;
    private int games_won_as_don;
    private int games_lost_as_don;
    private int games_won_as_sheriff;
    private int games_lost_as_sheriff;
    private int games_won_as_civilian;
    private int games_lost_as_civilian;
    private int games_won_as_mafia;
    private int games_lost_as_mafia;


    public User(String nickname, String password, int experience) {
        this.nickname = nickname;
        this.password = password;
        this.experience = experience;
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(String nickname, String password, String photo, int experience) {
        this.nickname = nickname;
        this.password = password;
        this.photo = photo;
        this.experience = experience;
    }

    public User(String nickname, String password, String photo, int experience, int games_played,
                int games_won, int games_lost, int games_won_as_red, int games_lost_as_red, int games_won_as_black,
                int games_lost_as_black, int games_won_as_don, int games_lost_as_don, int games_won_as_sheriff,
                int games_lost_as_sheriff, int games_won_as_civilian, int games_lost_as_civilian, int games_won_as_mafia, int games_lost_as_mafia) {
        this.nickname = nickname;
        this.password = password;
        this.photo = photo;
        this.experience = experience;
        this.games_played = games_played;
        this.games_won = games_won;
        this.games_lost = games_lost;
        this.games_won_as_red = games_won_as_red;
        this.games_lost_as_red = games_lost_as_red;
        this.games_won_as_black = games_won_as_black;
        this.games_lost_as_black = games_lost_as_black;
        this.games_won_as_don = games_won_as_don;
        this.games_lost_as_don = games_lost_as_don;
        this.games_won_as_sheriff = games_won_as_sheriff;
        this.games_lost_as_sheriff = games_lost_as_sheriff;
        this.games_won_as_civilian = games_won_as_civilian;
        this.games_lost_as_civilian = games_lost_as_civilian;
        this.games_won_as_mafia = games_won_as_mafia;
        this.games_lost_as_mafia = games_lost_as_mafia;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getGames_won() {
        return games_won;
    }

    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }

    public int getGames_lost() {
        return games_lost;
    }

    public void setGames_lost(int games_lost) {
        this.games_lost = games_lost;
    }

    public int getGames_won_as_red() {
        return games_won_as_red;
    }

    public void setGames_won_as_red(int games_won_as_red) {
        this.games_won_as_red = games_won_as_red;
    }

    public int getGames_lost_as_red() {
        return games_lost_as_red;
    }

    public void setGames_lost_as_red(int games_lost_as_red) {
        this.games_lost_as_red = games_lost_as_red;
    }

    public int getGames_won_as_black() {
        return games_won_as_black;
    }

    public void setGames_won_as_black(int games_won_as_black) {
        this.games_won_as_black = games_won_as_black;
    }

    public int getGames_lost_as_black() {
        return games_lost_as_black;
    }

    public void setGames_lost_as_black(int games_lost_as_black) {
        this.games_lost_as_black = games_lost_as_black;
    }

    public int getGames_won_as_don() {
        return games_won_as_don;
    }

    public void setGames_won_as_don(int games_won_as_don) {
        this.games_won_as_don = games_won_as_don;
    }

    public int getGames_lost_as_don() {
        return games_lost_as_don;
    }

    public void setGames_lost_as_don(int games_lost_as_don) {
        this.games_lost_as_don = games_lost_as_don;
    }

    public int getGames_won_as_sheriff() {
        return games_won_as_sheriff;
    }

    public void setGames_won_as_sheriff(int games_won_as_sheriff) {
        this.games_won_as_sheriff = games_won_as_sheriff;
    }

    public int getGames_lost_as_sheriff() {
        return games_lost_as_sheriff;
    }

    public void setGames_lost_as_sheriff(int games_lost_as_sheriff) {
        this.games_lost_as_sheriff = games_lost_as_sheriff;
    }

    public int getGames_won_as_civilian() {
        return games_won_as_civilian;
    }

    public void setGames_won_as_civilian(int games_won_as_civilian) {
        this.games_won_as_civilian = games_won_as_civilian;
    }

    public int getGames_lost_as_civilian() {
        return games_lost_as_civilian;
    }

    public void setGames_lost_as_civilian(int games_lost_as_civilian) {
        this.games_lost_as_civilian = games_lost_as_civilian;
    }

    public int getGames_won_as_mafia() {
        return games_won_as_mafia;
    }

    public void setGames_won_as_mafia(int games_won_as_mafia) {
        this.games_won_as_mafia = games_won_as_mafia;
    }

    public int getGames_lost_as_mafia() {
        return games_lost_as_mafia;
    }

    public void setGames_lost_as_mafia(int games_lost_as_mafia) {
        this.games_lost_as_mafia = games_lost_as_mafia;
    }
}
