package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;

public abstract class Controller implements com.upm.master.mastermind.controller.Controller {
   protected Game game;
   protected State state;

   public Controller(Game game, State state) {
      this.game = game;
      this.state = state;
   }

   public void setNextState() {
      state.setNextValue();
   }
}

