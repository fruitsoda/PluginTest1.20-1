package org.fruit.pluginTest1201.controller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;
import org.fruit.pluginTest1201.Main;
import org.bukkit.event.entity.EntityDeathEvent;
import org.fruit.pluginTest1201.entity.TeamNames;
import org.fruit.pluginTest1201.entity.User;
import org.fruit.pluginTest1201.service.UserManager;

import javax.lang.model.type.NullType;

public class KillCountEventController implements Listener {
    private final Main serverInstance;
    private final UserManager userManager;
    private Player player;
    public KillCountEventController(UserManager userManager){
        this.serverInstance = Main.getServerInstance();
        this.userManager = userManager;
    }

    @EventHandler
    public void onKillCount(EntityDeathEvent event){
        serverInstance.getLogger().info("onKillCount 이벤트 실행");
        if(event.getEntity().getKiller() != null){
            player = event.getEntity().getKiller();
        }
        try{
            userManager.addKillCounts(player);
            updateScoreBoard(player);
        } catch (NullPointerException e) {
            serverInstance.getLogger().info("NULL 값이 들어왔어용");
        }

    }

    public void updateScoreBoard(Player player){
        serverInstance.getLogger().info("updateScoreBoard 함수 실행");
        User user = userManager.getUserData(player);
        Scoreboard scoreboard = player.getScoreboard();
        Team killCount = scoreboard.getTeam(TeamNames.KILL_COUNT.toString());
        killCount.setSuffix(ChatColor.YELLOW + user.getKills().toString());
    }
}
