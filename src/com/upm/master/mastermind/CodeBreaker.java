package com.upm.master.mastermind;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CodeBreaker {
   private Game game;
   private Map<Code, Response> responses = new HashMap<>();

   public CodeBreaker(Game game) {
      this.game = game;
   }

   public Code play(int attempt) {
      Character chr;

      Code.Builder builder = new Code.Builder();
      game.getVisualizer().askCode(Game.FIGURES_TO_GUESS, attempt, game.getMaxAttempt());
      while (builder.size() < Game.FIGURES_TO_GUESS) {
         builder.add(readCharacter());
      }
      return builder.build();
   }

   public void store(Code code, Response response) {
      responses.put(code, response);
   }

   private Character readCharacter() {
      Character result;

      while (true) {
         game.getVisualizer().askCharacter(game.getValidFigures());
         try {
            result = game.getReader().readCharacter();
         } catch (IOException e) {
            result = '-';
         }

         if (game.getValidFigures().contains(result) == true) {
            break;
         }

         game.getVisualizer().notValid(result);
      }

      return result;
   }
}
