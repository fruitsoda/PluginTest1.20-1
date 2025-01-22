package org.fruit.pluginTest1201.controller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.scoreboard.*;
import org.fruit.pluginTest1201.Main;
import org.fruit.pluginTest1201.entity.User;
import org.fruit.pluginTest1201.service.UserManager;
import org.jetbrains.annotations.NotNull;

import java.rmi.ServerError;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        serverInstance.getLogger().info("createScoreBoard 실행");
        User user = userManager.getUserData(player);
        ScoreboardManager scoreboardManager =  Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        Objective o = board.registerNewObjective("testBoard", "dummy");
        o.setDisplayName(ChatColor.BOLD + "PLUGIN TEST SERVER");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = o.getScore(String.format("Players: %d", Bukkit.getOnlinePlayers().size()));
        score.setScore(3);
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formatedNow = now.format(formatter);
        score = o.getScore(formatedNow);
        score.setScore(4);
        score = o.getScore(user.getDisplayName());
        score.setScore(5);
        score = o.getScore(user.getMoney()+"원");
        score.setScore(2);
        score = o.getScore("킬 수: " + Long.toString(user.getKills()));
        score.setScore(1);
        player.setScoreboard(board);
    }

    public void updateScoreBoard(){
        for(Player online: Bukkit.getOnlinePlayers()){
            Scoreboard board = online.getScoreboard();
            Objective o = board.getObjective("testBoard");
            o.getScore(String.format("Players: %d", Bukkit.getOnlinePlayers().size())).resetScore();
        }
    }
}
