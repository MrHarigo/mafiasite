package ru.kpfu.itis.group11501.utkin.Models;

import ru.kpfu.itis.group11501.utkin.Services.Implementations.UserServiceImpl;

import java.sql.Date;

/**
 * Created by user on 18.11.2016.
 */
public class Game {
    private int id;
    private User player_civilian_1;
    private User player_civilian_2;
    private User player_civilian_3;
    private User player_civilian_4;
    private User player_civilian_5;
    private User player_civilian_6;
    private User player_mafia_1;
    private User player_mafia_2;
    private User player_don;
    private User player_sheriff;
    private Date date;
    private String winner_team;
    private UserServiceImpl userService = null;

    public Game(int id, User player_civilian_1, User player_civilian_2, User player_civilian_3, User player_civilian_4,
                User player_civilian_5, User player_civilian_6, User player_mafia_1, User player_mafia_2,
                User player_don, User player_sheriff, Date date, String winner_team) {

        this.id = id;
        this.player_civilian_1 = player_civilian_1;
        this.player_civilian_2 = player_civilian_2;
        this.player_civilian_3 = player_civilian_3;
        this.player_civilian_4 = player_civilian_4;
        this.player_civilian_5 = player_civilian_5;
        this.player_civilian_6 = player_civilian_6;
        this.player_mafia_1 = player_mafia_1;
        this.player_mafia_2 = player_mafia_2;
        this.player_don = player_don;
        this.player_sheriff = player_sheriff;
        this.date = date;
        this.winner_team = winner_team;
    }

    public Game(int id, String player_civilian_1, String player_civilian_2, String player_civilian_3, String player_civilian_4,
                String player_civilian_5, String player_civilian_6, String player_mafia_1, String player_mafia_2,
                String player_don, String player_sheriff, Date date, String winner_team) {
        userService = new UserServiceImpl();

        this.id = id;
        this.player_civilian_1 = userService.find(player_civilian_1);
        this.player_civilian_2 = userService.find(player_civilian_2);
        this.player_civilian_3 = userService.find(player_civilian_3);
        this.player_civilian_4 = userService.find(player_civilian_4);
        this.player_civilian_5 = userService.find(player_civilian_5);
        this.player_civilian_6 = userService.find(player_civilian_6);
        this.player_mafia_1 = userService.find(player_mafia_1);
        this.player_mafia_2 = userService.find(player_mafia_2);
        this.player_don = userService.find(player_don);
        this.player_sheriff = userService.find(player_sheriff);
        this.date = date;
        this.winner_team = winner_team;
    }

    public Game(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlayer_civilian_1() {
        return player_civilian_1;
    }

    public void setPlayer_civilian_1(User player_civilian_1) {
        this.player_civilian_1 = player_civilian_1;
    }

    public User getPlayer_civilian_2() {
        return player_civilian_2;
    }

    public void setPlayer_civilian_2(User player_civilian_2) {
        this.player_civilian_2 = player_civilian_2;
    }

    public User getPlayer_civilian_3() {
        return player_civilian_3;
    }

    public void setPlayer_civilian_3(User player_civilian_3) {
        this.player_civilian_3 = player_civilian_3;
    }

    public User getPlayer_civilian_4() {
        return player_civilian_4;
    }

    public void setPlayer_civilian_4(User player_civilian_4) {
        this.player_civilian_4 = player_civilian_4;
    }

    public User getPlayer_civilian_5() {
        return player_civilian_5;
    }

    public void setPlayer_civilian_5(User player_civilian_5) {
        this.player_civilian_5 = player_civilian_5;
    }

    public User getPlayer_civilian_6() {
        return player_civilian_6;
    }

    public void setPlayer_civilian_6(User player_civilian_6) {
        this.player_civilian_6 = player_civilian_6;
    }

    public User getPlayer_mafia_1() {
        return player_mafia_1;
    }

    public void setPlayer_mafia_1(User player_mafia_1) {
        this.player_mafia_1 = player_mafia_1;
    }

    public User getPlayer_mafia_2() {
        return player_mafia_2;
    }

    public void setPlayer_mafia_2(User player_mafia_2) {
        this.player_mafia_2 = player_mafia_2;
    }

    public User getPlayer_don() {
        return player_don;
    }

    public void setPlayer_don(User player_don) {
        this.player_don = player_don;
    }

    public User getPlayer_sheriff() {
        return player_sheriff;
    }

    public void setPlayer_sheriff(User player_sheriff) {
        this.player_sheriff = player_sheriff;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWinner_team() {
        return winner_team;
    }

    public void setWinner_team(String winner_team) {
        this.winner_team = winner_team;
    }
}
