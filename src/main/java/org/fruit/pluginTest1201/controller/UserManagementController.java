package org.fruit.pluginTest1201.controller;

import org.fruit.pluginTest1201.Main;
import org.fruit.pluginTest1201.controller.commands.GiveMoneyCmdController;
import org.fruit.pluginTest1201.service.UserManager;

public class UserManagementController {

    private final Main serverInstance;
    private UserConnectController userConnectController;
    private UserManager userManager;
    private ScoreBoardEventController scoreBoardEventController;
    private KillCountEventController killCountEventController;

    public UserManagementController(){
        this.serverInstance = Main.getServerInstance();
        this.userManager = new UserManager();
        this.userConnectController = new UserConnectController(this.userManager);
        this.scoreBoardEventController = new ScoreBoardEventController(this.userManager);
        this.killCountEventController = new KillCountEventController(this.userManager);

        registerEvent();
        registerCommand();
    }

    /*
        이벤트 등록 방법

        1. 해당 이벤트 컨트롤러 클래스를 멤버변수로 지정 및 초기화
        2. registerEvent 함수에 해당 이벤트를 밑에 양식과 똑같이 등록
    */
    public void registerEvent(){
        serverInstance.getServer().getPluginManager().registerEvents(userConnectController, serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(scoreBoardEventController, serverInstance);
        serverInstance.getServer().getPluginManager().registerEvents(killCountEventController, serverInstance);
    }

    public void registerCommand(){
        serverInstance.getServer().getPluginCommand("giveMoney").setExecutor(new GiveMoneyCmdController(userManager));
    }
}
