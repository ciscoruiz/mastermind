package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.MasterMind;
import com.upm.master.mastermind.view.MasterMindView;

public final class MasterMindController {
   private final MasterMindView view;
   private final Configuration configuration;
   private final GameController gameController;

   public MasterMindController(MasterMindView view, Configuration configuration, GameController gameController) {
      this.view = view;
      this.configuration = configuration;
      this.gameController = gameController;
   }

   public void play() {
      MasterMind masterMind  = new MasterMind(configuration);

      do {
         view.startGame(masterMind);
         masterMind.annotateResult(gameController.play(configuration));
         view.endGame(masterMind);
      } while (view.wantToPlayNewGame(masterMind));
   }
}
