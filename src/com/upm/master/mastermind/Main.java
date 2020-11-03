package com.upm.master.mastermind;

import com.upm.master.mastermind.controller.ConfigurationController;
import com.upm.master.mastermind.controller.GameController;
import com.upm.master.mastermind.controller.MasterMindController;
import com.upm.master.mastermind.view.ConfigurationView;
import com.upm.master.mastermind.view.auto.ConfigurationAuto;
import com.upm.master.mastermind.view.console.GameConsole;
import com.upm.master.mastermind.view.console.MasterMindConsole;

public class Main {
    public static void main(String[] args) {
       ConfigurationController configurationController = new ConfigurationController(new ConfigurationAuto());
       GameController gameController = new GameController(new GameConsole());

       MasterMindController masterMindController = new MasterMindController(new MasterMindConsole(), configurationController, gameController);

       masterMindController.play();
    }
}
