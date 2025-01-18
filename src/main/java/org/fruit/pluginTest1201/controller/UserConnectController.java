package org.fruit.pluginTest1201.controller;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.fruit.pluginTest1201.Main;
import org.fruit.pluginTest1201.service.UserManager;

public class UserConnectController implements Listener {
    private final Main serverInstance;
    private final UserManager userManager;

    public UserConnectController(UserManager userManager){
        this.serverInstance = Main.getServerInstance();
        this.userManager = userManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        userManager.addUser(event.getPlayer());
        serverInstance.getLogger().info("플레이어 데이터 저장!!!!!!!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        userManager.removeUser(event.getPlayer());
        serverInstance.getLogger().info("플레이어 데이터 삭제!!!!!!!");
    }

}
