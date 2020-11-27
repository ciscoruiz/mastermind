package com.upm.master.mastermind;

import com.upm.master.mastermind.controller.model.ControllersContainer;
import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.GameHistoryKeeper;
import com.upm.master.mastermind.model.State;
import com.upm.master.mastermind.view.ViewsContainer;

public class MasterMind {
   private ViewsContainer viewsContainer;
   private Game game;
   private State state = new State();
   private ControllersContainer controllersContainer;
   private GameHistoryKeeper gameHistoryKeeper;

   public MasterMind(ViewsContainer viewsContainer, Configuration configuration) {
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
}
