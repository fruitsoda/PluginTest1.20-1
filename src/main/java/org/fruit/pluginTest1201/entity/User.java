package org.fruit.pluginTest1201.entity;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String displayName;
    private Long money;
    private Long killPoints;
    private Long kills;

    public User (UUID uuid, @NotNull String displayName, long money, long killPoints, long kills){
        this.uuid = uuid;
        this.displayName = displayName;
        this.money = money;
        this.killPoints = killPoints;
        this.kills = kills;
    }

    public UUID getUUID(){
        return this.uuid;
    }

    public void setUUID(UUID uuid){
        this.uuid = uuid;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public void setDisplayName(@NotNull String displayName){
        this.displayName = displayName;
    }

    public Long getMoney(){
        return this.money;
    }

    public void setMoney(Long money){
        this.money = money;
    }

    public Long getKillPoints(Long killPoints){
        return this.killPoints;
    }

    public void setKillPoints(Long killPoints){
        this.killPoints = killPoints;
    }

    public Long getKills(){
        return this.kills;
    }

    public void setKills(Long kills){
        this.kills = kills;
    }
}
