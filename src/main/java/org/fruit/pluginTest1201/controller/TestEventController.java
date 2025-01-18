package org.fruit.pluginTest1201.controller;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.fruit.pluginTest1201.Main;

public class TestEventController implements Listener {

    private final Main serverInstance;

    public TestEventController(){
        this.serverInstance = Main.getServerInstance();
    }

    @EventHandler
    public void onTestEvent(){

    }
}
