package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;

public class ResumeController extends Controller {
   public ResumeController(Game game, State state) {
      super(game, state);
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }
   public void resume() { state.setValue(State.Value.INITIAL); }
   public void stop() { state.setValue(State.Value.STOP); }
}
