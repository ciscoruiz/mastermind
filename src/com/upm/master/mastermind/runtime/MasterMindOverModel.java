package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.controller.model.PlayModelController;
import com.upm.master.mastermind.controller.model.ResumeModelController;
import com.upm.master.mastermind.controller.model.StartModelController;
import com.upm.master.mastermind.model.*;
import com.upm.master.mastermind.view.ViewsContainer;

public abstract class MasterMindOverModel implements MasterMind {
   protected ViewsContainer viewsContainer;
   protected Game game;
   protected State state = new State();
   protected GameHistoryKeeper gameHistoryKeeper;

   MasterMindOverModel(ViewsContainer viewsContainer) {
      this.viewsContainer = viewsContainer;
      this.game = new Game(createHardcodedConfiguration());
      this.gameHistoryKeeper = new GameHistoryKeeper(game);
   }

   private static Configuration createHardcodedConfiguration() {
      Configuration configuration = new Configuration();

      configuration.setMaxAttempt(5);

      ValidFigures validFigures = new ValidFigures();
      validFigures.add('A').add('B').add('C').add('D').add('E').add('F');
      configuration.setValidFigures(validFigures);

      return configuration;
   }

   public StartController createStartController() {
      return new StartModelController(game, state);
   }
   public PlayController createPlayController() {
      return new PlayModelController(game,state, gameHistoryKeeper);
   }
   public ResumeController createResumeController() {  return new ResumeModelController(game, state, gameHistoryKeeper); }
}
