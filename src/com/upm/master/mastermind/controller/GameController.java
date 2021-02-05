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

      view.showSecretCode(game);

      boolean codeRemainsSecret;

      do {
         Code code = view.askGuessCode(game);
         Response response = game.evaluate(code);
         view.showResponse(code, response);
         codeRemainsSecret = !response.codeWasBroken();
      } while (game.breakerCanRetry() && codeRemainsSecret);

      if (codeRemainsSecret) {
         result.setMakerWins();
         view.makerWins(game);
      }
      else {
         result.setBreakerWins();
         view.breakerWins(game);
      }

      return result;
   }
}
