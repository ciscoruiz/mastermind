package com.upm.master.mastermind.runtime;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.ControllerAbstractFactory;
import com.upm.master.mastermind.controller.model.ControllerModelFactory;
import com.upm.master.mastermind.model.*;
import com.upm.master.mastermind.view.ViewsContainer;

public abstract class MasterMindOverModel implements MasterMind {
   protected ViewsContainer viewsContainer;
   protected Game game;
   protected State state = new State();
   protected GameHistoryKeeper gameHistoryKeeper;
   protected ControllerAbstractFactory controllerFactory;

   MasterMindOverModel(ViewsContainer viewsContainer) {
      this.viewsContainer = viewsContainer;
      this.game = new Game(createHardcodedConfiguration());
      this.gameHistoryKeeper = new GameHistoryKeeper(game);
      this.controllerFactory = new ControllerModelFactory(game, state, gameHistoryKeeper);
   }

   private static Configuration createHardcodedConfiguration() {
      Configuration configuration = new Configuration();

      configuration.setMaxAttempt(5);

      ValidFigures validFigures = new ValidFigures();
      validFigures.add('A').add('B').add('C').add('D').add('E').add('F');
      configuration.setValidFigures(validFigures);

      return configuration;
   }
}
