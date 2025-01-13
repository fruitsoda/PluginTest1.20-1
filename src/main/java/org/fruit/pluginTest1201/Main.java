package org.fruit.pluginTest1201;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main serverInstance;
    @Override
    public void onEnable() {
       getLogger().info("hello minecraft server!!!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getServerInstance(){
        return serverInstance;
    }
}
