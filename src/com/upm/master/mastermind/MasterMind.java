package com.upm.master.mastermind;

import com.upm.master.mastermind.controller.ControllersContainer;
import com.upm.master.mastermind.model.Configuration;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;
import com.upm.master.mastermind.view.ViewsContainer;

public class MasterMind {
   private ViewsContainer viewsContainer;
   private Game game;
   private State state = new State();
   private ControllersContainer controllersContainer;

   public MasterMind(ViewsContainer viewsContainer, Configuration configuration) {
      this.viewsContainer = viewsContainer;
      this.game = new Game(configuration);
      this.controllersContainer = new ControllersContainer(game, state);
   }

   public void play() {
      while (state.continuePlaying()) {
         viewsContainer.updateView(controllersContainer.getController(state));
      }
   }
}
