package com.upm.master.mastermind.controller;

import com.upm.master.mastermind.model.*;
import com.upm.master.mastermind.view.GameView;

public class GameController {
   private final GameView view;

   public GameController(GameView view) {
      this.view = view;
   }

   Rating play(Configuration configuration) {
      Game game = new Game(configuration);
      Rating result = new Rating();

      game.generateSecretCode();
      view.showSecretCode(game);

      while (game.continuePlaying()) {
         Code code = view.askGuessCode(game);
         Response response = game.evaluate(code);

         view.showResponse(code, response);

         if (response.codeWasBroken()) {
            result.setBreakerWins();
            view.breakerWins(game);
            return result;
         }
      }

      result.setMakerWins();
      view.makerWins(game);
      return result;
   }
}
