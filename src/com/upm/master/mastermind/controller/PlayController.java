package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.*;

public class PlayController extends Controller {
   GameHistoryKeeper gameHistoryKeeper;
   public PlayController(Game game, State state, GameHistoryKeeper gameHistoryKeeper) {
      super(game, state);
      this.gameHistoryKeeper = gameHistoryKeeper;
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public boolean continuePlaying() {
      return game.continuePlaying();
   }

   public Response evaluate(Code guessCode) {
      return game.evaluate(guessCode);
   }

   public ValidFigures getValidFigures() {
      return game.getValidFigures();
   }

   public int getAttempt() {
      return game.getAttempt();
   }

   public int getMaxAttempt() {
      return game.getMaxAttempt();
   }

   public void codeBreakerWins() {
      setNextState();
   }

   public void codeMakerWins() {
      setNextState();
   }

   public void registry() {
      gameHistoryKeeper.registry();
   }

   public void undo() {
      gameHistoryKeeper.undo();
   }

   public void redo() {
      gameHistoryKeeper.redo();
   }

   public boolean canApplyUndo() {
      return gameHistoryKeeper.canApplyUndo();
   }

   public boolean canApplyRedo() {
      return gameHistoryKeeper.canApplyRedo();
   }
}
