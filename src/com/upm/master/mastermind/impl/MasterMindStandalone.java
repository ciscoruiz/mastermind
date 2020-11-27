package com.upm.master.mastermind.impl;

import com.upm.master.mastermind.MasterMind;
import com.upm.master.mastermind.controller.model.ControllersContainer;
import com.upm.master.mastermind.model.*;
import com.upm.master.mastermind.view.ViewsContainer;
import com.upm.master.mastermind.view.console.ViewsContainerConsole;

public class MasterMindStandalone implements MasterMind {
   private ViewsContainer viewsContainer;
   private Game game;
   private State state = new State();
   private GameHistoryKeeper gameHistoryKeeper;
   private ControllersContainer controllersContainer;

   static public MasterMind create() {
      ViewsContainer viewsContainer = new ViewsContainerConsole();
      return new MasterMindStandalone(viewsContainer, createHardcodedConfiguration());
   }

   MasterMindStandalone(ViewsContainer viewsContainer, Configuration configuration) {
      this.viewsContainer = viewsContainer;
      this.game = new Game(configuration);
      this.gameHistoryKeeper = new GameHistoryKeeper(game);
      this.controllersContainer = new ControllersContainer(game, state, gameHistoryKeeper);
   }

   public void play() {
      while (state.continuePlaying()) {
         viewsContainer.updateView(controllersContainer.getController(state));
      }
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
