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

      if (playerBreaksCode(game)) {
         result.setPlayerWins();
         view.playerWins(game);
      }
      else {
         result.setMakerWins();
         view.makerWins(game);
      }

      return result;
   }

   private boolean playerBreaksCode(Game game) {
      boolean codeIsBroken;

      do {
         Code code = view.askGuessCode(game);
         Response response = game.evaluate(code);
         view.showResponse(code, response);
         codeIsBroken = response.codeWasBroken();
      } while (game.breakerCanRetry() && !codeIsBroken);

      return codeIsBroken;
   }
}
