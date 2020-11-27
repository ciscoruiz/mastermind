package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.ResumeController;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.GameHistoryKeeper;
import com.upm.master.mastermind.model.State;

class ResumeModelController extends ModelController implements ResumeController {
   private GameHistoryKeeper gameHistoryKeeper;

   ResumeModelController(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      super(game, state);
      this.gameHistoryKeeper = gameHistoryKeeper;
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public void resume() {
      state.setValue(State.Value.INITIAL);
      gameHistoryKeeper.clear();
   }

   public void stop() {
      state.setValue(State.Value.STOP);
   }
}
