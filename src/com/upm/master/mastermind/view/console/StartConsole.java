package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.controller.model.StartModelController;
import com.upm.master.mastermind.model.Code;

public class StartConsole {
   public void update(StartModelController startController) {
      System.out.println("Start new play for MasterMind v2.0");
      startController.initializeGame();
      Code code = startController.getSecretCode();
      System.out.println("Code to break: " + code.toString());
      startController.setNextState();
   }
}
