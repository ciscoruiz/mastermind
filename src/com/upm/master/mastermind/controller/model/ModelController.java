package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.Controller;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;

public abstract class ModelController implements Controller {
   protected Game game;
   protected State state;

   public ModelController(Game game, State state) {
      this.game = game;
      this.state = state;
   }

   public void setNextState() {
      state.setNextValue();
   }
}

