package com.upm.master.mastermind.controller.model;

import com.upm.master.mastermind.controller.ControllerVisitor;
import com.upm.master.mastermind.controller.StartController;
import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.State;

public class StartModelController extends ModelController implements StartController {
   public StartModelController(Game game, State state) {
      super(game, state);
   }

   @Override
   public void accept(ControllerVisitor controllerVisitor) {
      controllerVisitor.visit(this);
   }

   public void initializeGame() {
      game.initialize();
      game.generateSecretCode();
   }

   public Code getSecretCode() {
      return game.getSecretCode();
   }
}
