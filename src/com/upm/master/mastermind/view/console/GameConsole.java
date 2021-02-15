package com.upm.master.mastermind.view.console;

import com.upm.master.mastermind.model.Code;
import com.upm.master.mastermind.model.Game;
import com.upm.master.mastermind.model.Response;
import com.upm.master.mastermind.view.GameView;

import java.util.Vector;

public class GameConsole implements GameView {
   KeyboardReader reader = new KeyboardReader();

   @Override
   public void showSecretCode(Game game) {
      System.out.println("Code to break: " + game.getSecretCode().toString());
   }

   @Override
   public void playerWins(Game game) {
      System.out.println("CodeBreaker WINS!!");
   }

   @Override
   public void makerWins(Game game) {
      System.out.println("CodeMaker WINS!!");
   }

   @Override
   public Code askGuessCode(Game game) {
      System.out.println(
         "--- Choose " + Game.FIGURES_TO_GUESS + " figures to try to guess code. Attempt " +
         game.getAttempt() + " of " + game.getMaxAttempt()
      );

      Vector<Character> figures = game.getValidFigures().getFigures();
      Code.Builder builder = new Code.Builder();

      String code = reader.readString("Press key for one character", Game.FIGURES_TO_GUESS, figures);
      for (int ii = 0; ii < code.length(); ++ ii) {
         builder.add(code.charAt(ii));
      }
      return builder.build();
   }

   @Override
   public void showResponse(Code code, Response response) {
      System.out.println(code.toString() + " -> " + response.toString());
   }
}
