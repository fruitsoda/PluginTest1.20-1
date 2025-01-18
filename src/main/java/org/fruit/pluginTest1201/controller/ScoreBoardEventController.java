package org.fruit.pluginTest1201.controller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;
import org.fruit.pluginTest1201.Main;
import org.fruit.pluginTest1201.service.UserManager;
import org.jetbrains.annotations.NotNull;

public class ScoreBoardEventController implements Listener {
    private final Main serverInstance;
    private final UserManager userManager;

    public ScoreBoardEventController(UserManager userManager){
        this.serverInstance = Main.getServerInstance();
        this.userManager = userManager;
    }

    @EventHandler
    public void onPlayerJoinBoard(PlayerJoinEvent event){
        createScoreBoard(event.getPlayer());
        updateScoreBoard();
    }

    @EventHandler
    public void onPlayerQuitBoard(PlayerQuitEvent event){
        updateScoreBoard();
    }

    public void createScoreBoard(@NotNull Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective o = board.registerNewObjective("PLUGIN TEST SERVER!!!", "dummy");
        o.setDisplayName(ChatColor.BOLD + "PLUGIN TEST SERVER");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = o.getScore("Players: ");
        score = o.getScore("TEST!!!!!!!");
        score.setScore(Bukkit.getOnlinePlayers().size());
        player.setScoreboard(board);
    }

    public void updateScoreBoard(){
        for(Player online: Bukkit.getOnlinePlayers()){
            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Players: ");
            score.setScore(Bukkit.getOnlinePlayers().size());
        }
    }
}
