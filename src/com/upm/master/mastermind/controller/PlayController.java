package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.*;

public class PlayController extends Controller {
   public PlayController(Game game, State state) {
      super(game, state);
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
}
