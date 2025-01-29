package org.fruit.pluginTest1201.controller;

import io.papermc.paper.event.player.ChatEvent;
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
import org.fruit.pluginTest1201.entity.TeamNames;
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

    private void createScoreBoard(@NotNull Player player){
        serverInstance.getLogger().info("createScoreBoard 실행");
        User user = userManager.getUserData(player);
        ScoreboardManager scoreboardManager =  Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        Objective o = board.registerNewObjective("testBoard", "dummy");
        o.setDisplayName(ChatColor.BOLD + "PLUGIN TEST SERVER");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formatedNow = now.format(formatter);
        Score score = o.getScore(formatedNow);
        score.setScore(4);
        score = o.getScore(user.getDisplayName());
        score.setScore(5);

        createPlayerCountScoreboard(board, o);
        createKillCountScoreboard(board, o, user);
        createMoneyScoreboard(board,o,user);

        player.setScoreboard(board);
    }

    private void updateScoreBoard(){
        for(Player online: Bukkit.getOnlinePlayers()){
            Scoreboard board = online.getScoreboard();
            Team countPlayers = board.getTeam(ChatColor.RED.toString());
            countPlayers.setSuffix(ChatColor.RED + String.format("%d", Bukkit.getOnlinePlayers().size()));
        }
    }

    private void createKillCountScoreboard(Scoreboard board, Objective o, User user){
        Team killCount = board.registerNewTeam(TeamNames.KILL_COUNT.toString());
        killCount.addEntry(ChatColor.YELLOW.toString());
        killCount.setPrefix("킬 수: ");
        killCount.setSuffix(ChatColor.YELLOW + user.getKills().toString());
        o.getScore(ChatColor.YELLOW.toString()).setScore(1);
    }

    private void createPlayerCountScoreboard(Scoreboard board, Objective o){
        Team countPlayers = board.registerNewTeam(TeamNames.CNT_PLAYERS.toString());
        countPlayers.addEntry(ChatColor.RED.toString());
        countPlayers.setPrefix("Players: ");
        countPlayers.setSuffix(ChatColor.RED + String.format("%d",Bukkit.getOnlinePlayers().size()));
        o.getScore(ChatColor.RED.toString()).setScore(3);
    }

    private void createMoneyScoreboard(Scoreboard board, Objective o, User user){
        Team money = board.registerNewTeam(TeamNames.MONEY.toString());
        money.addEntry(ChatColor.GOLD.toString());
        money.setPrefix("돈: ");
        money.setSuffix(ChatColor.GOLD + String.format("%d", user.getMoney()));
        o.getScore(ChatColor.GOLD.toString()).setScore(2);
    }
}
