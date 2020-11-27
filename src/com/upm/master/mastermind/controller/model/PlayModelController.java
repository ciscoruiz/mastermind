package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.PlayController;
import com.upm.master.mastermind.model.*;

public class PlayModelController extends ModelController implements PlayController {
   private GameHistoryKeeper gameHistoryKeeper;

   public PlayModelController(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      super(game, state);
      this.gameHistoryKeeper = gameHistoryKeeper;
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public boolean continueGame() { return game.continuePlaying() && state.getValue() == State.Value.PLAYING; }

   public Response evaluate(Code guessCode) {  return game.evaluate(guessCode);  }

   public ValidFigures getValidFigures() {  return game.getValidFigures();  }

   public int getAttempt() {  return game.getAttempt();  }

   public int getMaxAttempt() { return game.getMaxAttempt(); }

   public void codeBreakerWins() { setNextState();  }

   public void codeMakerWins() {  setNextState();  }

   public void registry() {  gameHistoryKeeper.registry(); }

   public void undo() { gameHistoryKeeper.undo(); }

   public void redo() { gameHistoryKeeper.redo(); }

   public void quit() { state.setValue(State.Value.QUIT); }

   public boolean codeDiscovered() { return state.getValue() == State.Value.RESUME; }

   public boolean isAborted() { return state.getValue() == State.Value.QUIT; }

   public boolean canApplyUndo() {  return gameHistoryKeeper.canApplyUndo(); }

   public boolean canApplyRedo() {  return gameHistoryKeeper.canApplyRedo(); }
}
