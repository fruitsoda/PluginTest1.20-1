package org.fruit.pluginTest1201.service;

import org.bukkit.entity.Player;
import org.fruit.pluginTest1201.entity.User;

import java.util.HashMap;

public class UserManager {
    private HashMap<Player, User> onlineUserData = new HashMap<>();

    public void addUser(Player player){
        User newUser = new User(
                player.getUniqueId(),
                player.getDisplayName(),
                1000L,
                0L,
                0L
        );
        onlineUserData.put(player, newUser);
    }

    public void removeUser(Player player){
        onlineUserData.remove(player);
    }

    public User getUserData(Player player){
        return onlineUserData.get(player);
    }

    public void addKillCounts(Player player){
        User user = getUserData(player);
        user.setKills(user.getKills() + 1L);
    }
}
