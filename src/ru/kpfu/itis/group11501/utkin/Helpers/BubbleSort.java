package ru.kpfu.itis.group11501.utkin.Helpers;

import ru.kpfu.itis.group11501.utkin.Models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 23.11.2016.
 */
public class BubbleSort {
    public static void sortUsersByGames(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getGames_played() < users.get(j + 1).getGames_played()) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }

    public static void sortUsersByWins(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getGames_won() < users.get(j + 1).getGames_won()) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }

    public static void sortUsersByRedWins(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getGames_won_as_red() < users.get(j + 1).getGames_won_as_red()) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }

    public static void sortUsersByBlackWins(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getGames_won_as_black() < users.get(j + 1).getGames_won_as_black()) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }

    public static void sortUsersByNickname(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getNickname().compareTo(users.get(j + 1).getNickname()) > 0) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }

    public static void sortUsersByExperience(ArrayList<User> users) {
        for (int i = users.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (users.get(j).getExperience() < users.get(j + 1).getExperience()) {
                    User help = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,help);
                }
            }
        }
    }
}
