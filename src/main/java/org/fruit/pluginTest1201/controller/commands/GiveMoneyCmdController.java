package org.fruit.pluginTest1201.controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.fruit.pluginTest1201.entity.TeamNames;
import org.fruit.pluginTest1201.entity.User;
import org.fruit.pluginTest1201.service.UserManager;
import org.jetbrains.annotations.NotNull;

public class GiveMoneyCmdController implements CommandExecutor {
    private final UserManager userManager;
    final Long PLUS_M;
    public GiveMoneyCmdController(UserManager userManager){
        this.userManager = userManager;
        this.PLUS_M = 1000L;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,@NotNull String label, @NotNull String[] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 입력이 가능합니다.");
            return false;
        }

        Player player = (Player) sender;
        User playerData = userManager.getUserData(player);
        if(args.length == 1){

            Long money = Long.valueOf(args[0]);
            playerData.setMoney(playerData.getMoney()+money);
            player.sendMessage("돈이 "+money.toString()+"원 충전되었습니다!");
            updateMoneyScoreboard(player, playerData);
        }else if(args.length == 0){
            playerData.setMoney(playerData.getMoney()+PLUS_M);
            player.sendMessage("돈이 "+PLUS_M.toString()+"원 충전되었습니다!");
            updateMoneyScoreboard(player, playerData);
        }else if(args.length > 1){
            player.sendMessage("잘못된 명령어 입니다!!!!");
        }


        return false;
    }

    private void updateMoneyScoreboard(Player player, User playerData){
        Scoreboard scoreboard = player.getScoreboard();
        Team money = scoreboard.getTeam(TeamNames.MONEY.toString());
        money.setSuffix(ChatColor.GOLD + String.format("%d", playerData.getMoney()));
    }
}
