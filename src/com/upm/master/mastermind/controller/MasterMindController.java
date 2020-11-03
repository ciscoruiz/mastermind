package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.Configuration;
import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.view.MasterMindView;

public final class MasterMindController {
   private final MasterMindView view;
   private final ConfigurationController configurationController;
   private final GameController gameController;
   MasterMind masterMind = null;

   public MasterMindController(MasterMindView view, ConfigurationController configurationController, GameController gameController) {
      this.view = view;
      this.configurationController = configurationController;
      this.gameController = gameController;
   }

   public void play() {
      Configuration configuration = configurationController.requestConfiguration();

      masterMind = new MasterMind(configuration);

      boolean startNewGame = true;

      while (startNewGame) {
         view.startGame(masterMind);
         masterMind.summarize(gameController.play(configuration));
         view.endGame(masterMind);
         startNewGame = masterMind.continuePlaying() && view.askPlayNewGame(masterMind);
      }
   }
}
