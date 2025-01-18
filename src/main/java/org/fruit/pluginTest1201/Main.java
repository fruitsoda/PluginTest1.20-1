package org.fruit.pluginTest1201;

import org.bukkit.plugin.java.JavaPlugin;
import org.fruit.pluginTest1201.controller.UserManagementController;

public final class Main extends JavaPlugin {

    private static Main serverInstance;
    private static UserManagementController userManagement;
    @Override
    public void onEnable() {
       serverInstance = this;
       userManagement = new UserManagementController();

    }

    @Override
    public void onDisable() {
        serverInstance = null;
        userManagement = null;
    }

    public static Main getServerInstance(){
        return serverInstance;
    }
}
