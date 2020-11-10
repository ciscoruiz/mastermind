package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;

public class StartController extends Controller {
   public StartController(Game game, State state) { super(game, state); }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {  controllerVisitor.visit(this); }

   public void initializeGame() {
      game.initalize();
      game.generateSecretCode();
   }

   public Code getSecretCode() {
      return game.getSecretCode();
   }
}
